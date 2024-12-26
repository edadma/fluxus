package io.github.edadma.fluxus

import org.scalajs.dom
import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
import scala.concurrent.Future
import scala.scalajs.js
import scala.scalajs.js.JSConverters.*

case class MockEndpoint(
    path: String,
    method: String = "GET",
    response: (Map[String, String], Map[String, String]) => Either[MockError, String] = (_, _) => Right("{}"),
    responseDelay: Int = 0,
)

case class MockError(
    status: Int,
    statusText: String,
)

class MockServer(endpoints: MockEndpoint*):
  private val routes = endpoints.map(e => (e.path, e)).toMap

  def overrideFetch(): Unit =
    js.Dynamic.global.fetch =
      (url: String, init: dom.RequestInit) =>
        logger.debug(
          "Mock fetch called",
          category = "Test",
          Map("url" -> url),
        )

        handle(url, init).toJSPromise

  def handle(url: String, init: dom.RequestInit): Future[dom.Response] =
    logger.debug(
      "MockServer handling request",
      category = "MockServer",
      Map(
        "url"         -> url,
        "method"      -> Option(init).map(_.method).getOrElse("GET"),
        "knownRoutes" -> routes.keys.mkString(", "),
      ),
    )

    routes.get(url) match
      case None =>
        logger.debug(
          "Route not found",
          category = "MockServer",
          Map("url" -> url),
        )

        Future.successful(
          new dom.Response(
            "Not Found",
            new dom.ResponseInit {
              status = 404
              statusText = "Not Found"
            },
          ),
        )

      case Some(endpoint) =>
        logger.debug(
          "Found matching endpoint",
          category = "MockServer",
          Map(
            "url" -> url,
          ),
        )

        endpoint.response(Map(), Map()) match
          case Right(body) =>
            Future.successful(
              new dom.Response(
                body,
                new dom.ResponseInit {
                  status = 200
                  statusText = "OK"
                },
              ),
            )
          case Left(error) =>
            Future.successful(
              new dom.Response(
                error.statusText,
                new dom.ResponseInit {
                  status = error.status
                  statusText = error.statusText
                },
              ),
            )
