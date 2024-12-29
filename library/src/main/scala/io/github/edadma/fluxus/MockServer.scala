package io.github.edadma.fluxus

import io.github.edadma.fluxus.core.RouteParser
import org.scalajs.dom
import zio.json.*

import org.scalajs.macrotaskexecutor.MacrotaskExecutor.Implicits.global
import scala.concurrent.Future
import scala.scalajs.js
import scala.scalajs.js.JSConverters.*

case class MockRequest(
    path: String,
    method: String,
    params: Map[String, String],     // Path parameters
    query: Map[String, Seq[String]], // Query parameters
    headers: Map[String, String] = Map.empty,
    body: Option[String] = None,
) {
  // Helper to get single query parameter (first value if multiple exist)
  def queryParam(name: String): Option[String] =
    query.get(name).flatMap(_.headOption)

  def param(name: String): Option[String] = params.get(name)

  def header(name: String): Option[String] = headers.get(name)
}

// Response builder for chaining
class MockResponse {
  private var responseStatus: Int          = 200
  private var responseStatusText: String   = ""
  private var responseHeaders              = Map.empty[String, String]
  private var responseBody: Option[String] = None

  def status(code: Int, text: String = ""): MockResponse = {
    responseStatus = code
    responseStatusText = text
    this
  }

  def header(name: String, value: String): MockResponse = {
    responseHeaders += (name -> value)
    this
  }

  def json[T: JsonEncoder](body: T): MockResponse = {
    responseHeaders += ("Content-Type" -> "application/json")
    responseBody = Some(body.toJson)
    this
  }

  def send(body: String): MockResponse = {
    responseBody = Some(body)
    this
  }

  // Internal method to build DOM Response
  private[fluxus] def toDOMResponse: dom.Response = {
    new dom.Response(
      responseBody.getOrElse(""),
      js.Dynamic.literal(
        status = responseStatus, // Now using our renamed field
        statusText = responseStatusText,
        headers = scalajs.js.Dictionary(responseHeaders.toSeq*),
      ).asInstanceOf[dom.ResponseInit],
    )
  }

  override def toString: String =
    s"MockResponse(status = $responseStatus, headers = $responseHeaders, body = $responseBody"
}

case class MockEndpoint(
    path: String,
    method: String = "GET",
    handler: MockRequest => MockResponse,
    responseDelay: Int = 0,
)

case class MockError(
    status: Int,
    statusText: String,
)

class MockServer(endpoints: MockEndpoint*) {
  def overrideFetch(): Unit =
    js.Dynamic.global.fetch =
      (url: String, init: dom.RequestInit) =>
        logger.debug(
          "Mock fetch called",
          category = "Test",
          Map("url" -> url),
        )

        handle(url, init).toJSPromise

  def handle(url: String, init: dom.RequestInit): Future[dom.Response] = {
    // Find matching endpoint
    endpoints.find { endpoint =>
      RouteParser.parse(endpoint.path, url).isDefined &&
      Option(init).map(_.method).getOrElse("GET") == endpoint.method
    } match {
      case Some(endpoint) =>
        val routeMatch = RouteParser.parse(endpoint.path, url).get
        val request = MockRequest(
          path = url.split("\\?")(0),
          method = Option(init).map(_.method).getOrElse("GET").toString,
          params = routeMatch.pathParams,
          query = routeMatch.queryParams,
          headers = Option(init)
            .flatMap(i => Option(i.headers))
            .map { h =>
              val headers = h.asInstanceOf[js.Dynamic]

              if (js.isUndefined(headers)) {
                Map.empty[String, String]
              } else if (js.isUndefined(headers.get)) {
                // Assume it's a dictionary-like object
                js.Object.keys(headers.asInstanceOf[js.Object])
                  .map(key => (key, headers.selectDynamic(key).asInstanceOf[String]))
                  .toMap
              } else {
                // It's a Headers object
                headers.keys().asInstanceOf[js.Array[String]]
                  .map(key => (key, headers.get(key).asInstanceOf[String]))
                  .toMap
              }
            }
            .getOrElse(Map.empty),
        )

        val response = endpoint.handler(request)

        Future.successful(response.toDOMResponse)
      case None =>
        Future.successful(new MockResponse().status(404)
          .send("Not Found")
          .toDOMResponse)
    }
  }
}
