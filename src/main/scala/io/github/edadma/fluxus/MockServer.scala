package io.github.edadma.fluxus

import org.scalajs.dom

import scala.concurrent.Future

// First, let's create the basic types
case class MockEndpoint(
    path: String,
    method: String = "GET",
    response: Either[MockError, String] = Right("{}"),
    responseDelay: Int = 0,
)

case class MockError(
    status: Int,
    statusText: String,
)

class MockServer(endpoints: MockEndpoint*):
  private var routes = endpoints.map(e => (e.path, e)).toMap

  def handle(url: String, init: dom.RequestInit): Future[dom.Response] =
    routes.get(url) match
      case None =>
        // Return 404 for unknown routes
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
        endpoint.response match
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
