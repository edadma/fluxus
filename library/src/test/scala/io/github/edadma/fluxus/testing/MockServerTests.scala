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
        response = { (params, _) =>
          capturedId = params("id")
          Right("""{"id": "123", "name": "Test User"}""")
        },
      ),
    )

    server.overrideFetch()

    // Test that "/api/users/123" matches and extracts id
    server.handle("/api/users/123", new dom.RequestInit {}).map { response =>
      response.status shouldBe 200
      capturedId shouldBe "123"
    }
  }

  it should "parse query parameters" in {
    var capturedQuery = Map.empty[String, String]

    val server = MockServer(
      MockEndpoint(
        path = "/api/users",
        response = { (_, query) =>
          capturedQuery = query
          Right("""{"users": []}""")
        },
      ),
    )

    server.overrideFetch()

    // Test that query parameters are parsed
    server.handle("/api/users?page=1&limit=10", new dom.RequestInit {}).map { response =>
      response.status shouldBe 200
      capturedQuery should contain allOf (
        "page"  -> "1",
        "limit" -> "10",
      )
    }
  }
}
