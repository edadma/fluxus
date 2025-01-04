package io.github.edadma.fluxus.testing

import io.github.edadma.fluxus.*
import io.github.edadma.fluxus.core.*
import org.scalajs.dom
import zio.json.*

import scala.scalajs.js

class MockServerTests extends AsyncDOMSpec {
  "MockServer" should "handle basic route parameters" in {
    var capturedId = ""

    val server = MockServer(
      MockEndpoint(
        path = "/api/users/:id",
        handler = req => {
          capturedId = req.params("id")
          new MockResponse()
            .status(200)
            .json(Map("id" -> "123", "name" -> "Test User"))
        },
      ),
    )

    server.overrideFetch()

    server.handle("/api/users/123", new dom.RequestInit { method = dom.HttpMethod.GET }).map { response =>
      response.status shouldBe 200
      capturedId shouldBe "123"
    }
  }

  it should "parse query parameters" in {
    var capturedQuery = Map.empty[String, Seq[String]]

    val server = MockServer(
      MockEndpoint(
        path = "/api/users",
        handler = req => {
          capturedQuery = req.query
          new MockResponse()
            .status(200)
            .json(Map("users" -> List()))
        },
      ),
    )

    server.overrideFetch()

    server.handle("/api/users?page=1&limit=10", new dom.RequestInit { method = dom.HttpMethod.GET }).map { response =>
      response.status shouldBe 200
      capturedQuery should contain allOf (
        "page"  -> Seq("1"),
        "limit" -> Seq("10"),
      )
    }
  }

  it should "handle both route and query parameters" in {
    val server = MockServer(
      MockEndpoint(
        path = "/api/users/:userId/posts",
        handler = req => {
          new MockResponse()
            .status(200)
            .json(Map(
              "userId" -> req.params("userId"),
              "page"   -> req.query.get("page").flatMap(_.headOption).getOrElse("1"),
            ))
        },
      ),
    )

    server.overrideFetch()

    server.handle("/api/users/123/posts?page=2", new dom.RequestInit { method = dom.HttpMethod.GET }).flatMap {
      response =>
        response.status shouldBe 200
        response.text().toFuture.map { text =>
          text should include("123") // userId
          text should include("2")   // page
        }
    }
  }

  it should "handle JSON request bodies and payloads correctly" in {
    case class Item(name: String, quantity: Int) derives JsonEncoder, JsonDecoder
    case class Response(
        success: Boolean,
        item: Option[Item] = None,
        error: Option[String] = None,
    ) derives JsonEncoder, JsonDecoder

    val server = MockServer(
      MockEndpoint(
        path = "/api/items",
        method = "POST",
        handler = req => {
          req.jsonBody[Item] match {
            case Some(item) =>
              new MockResponse()
                .status(201)
                .json(Response(success = true, item = Some(item)))
            case None =>
              new MockResponse()
                .status(400)
                .json(Response(success = false, error = Some("Invalid request body")))
          }
        },
      ),
    )

    val payload = Item("test item", 5)

    for {
      response <- server.handle(
        "/api/items",
        new dom.RequestInit {
          method = dom.HttpMethod.POST
          body = payload.toJson
          headers = js.Dictionary("Content-Type" -> "application/json")
        },
      )
      _ = response.status shouldBe 201
      text <- response.text().toFuture
      _ = text.fromJson[Response] match {
        case Right(res) =>
          res.success shouldBe true
          res.item shouldBe Some(payload)
        case Left(error) => fail(s"Failed to parse response: $error")
      }
      invalidResponse <- server.handle(
        "/api/items",
        new dom.RequestInit {
          method = dom.HttpMethod.POST
          body = """{"invalid": "json"}"""
          headers = js.Dictionary("Content-Type" -> "application/json")
        },
      )
      _ = invalidResponse.status shouldBe 400
      invalidText <- invalidResponse.text().toFuture
    } yield invalidText.fromJson[Response].toOption.get.success shouldBe false
  }
}
