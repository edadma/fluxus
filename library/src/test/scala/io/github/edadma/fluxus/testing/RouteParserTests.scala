package io.github.edadma.fluxus.testing

import io.github.edadma.fluxus.core.RouteParser
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class RouteParserTests extends AnyFlatSpec with Matchers {
  "RouteParser" should "parse complete URLs with path and query parameters" in {
    val pattern = "/api/users/:userId/posts/:postId"
    val url     = "/api/users/123/posts/456?sort=desc&limit=10"

    val result = RouteParser.parse(pattern, url)

    result shouldBe defined
    result.get.pathParams should contain allOf (
      "userId" -> "123",
      "postId" -> "456",
    )
    result.get.queryParams should contain allOf (
      "sort"  -> Seq("desc"),
      "limit" -> Seq("10"),
    )
  }

  it should "handle missing query parameters" in {
    val pattern = "/api/users/:id"
    val url     = "/api/users/123"

    val result = RouteParser.parse(pattern, url)

    result shouldBe defined
    result.get.pathParams should contain("id" -> "123")
    result.get.queryParams shouldBe empty
  }

  it should "handle empty query parameters" in {
    val pattern = "/api/users/:id"
    val url     = "/api/users/123?"

    val result = RouteParser.parse(pattern, url)

    result shouldBe defined
    result.get.pathParams should contain("id" -> "123")
    result.get.queryParams shouldBe empty
  }

  it should "return None for non-matching routes" in {
    val pattern = "/api/users/:id"
    val url     = "/api/posts/123"

    RouteParser.parse(pattern, url) shouldBe None
  }

  it should "handle multiple query parameters with same name" in {
    val pattern = "/api/users"
    val url     = "/api/users?tag=scala&tag=js"

    val result = RouteParser.parse(pattern, url)

    result shouldBe defined
    result.get.queryParams should contain("tag" -> Seq("scala", "js"))
  }
}
