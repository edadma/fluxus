package io.github.edadma.fluxus.testing

import io.github.edadma.fluxus.*
import io.github.edadma.fluxus.core.*
import org.scalajs.dom

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
}
