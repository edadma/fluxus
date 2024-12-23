package io.github.edadma.fluxus.testing

import io.github.edadma.fluxus.*
import io.github.edadma.fluxus.core.*
import org.scalajs.dom
import zio.json.*
import scala.scalajs.js

class FetchTests extends AsyncDOMSpec {
  val mockServer = MockServer(
    MockEndpoint(
      path = "/api/items",
      response = Right("""[
        {"id": 1, "name": "First Item"},
        {"id": 2, "name": "Second Item"},
        {"id": 3, "name": "Third Item"}
      ]"""),
    ),
    MockEndpoint(
      path = "/api/not-found",
      response = Left(MockError(404, "Not Found")),
    ),
  )

  mockServer.overrideFetch()

  "useFetch" should "successfully fetch and render a list of items" in withDebugLogging(
    "successfully fetch and render a list of items",
  ) {
    case class Item(id: Int, name: String) derives JsonDecoder

    val container = getContainer

    def TestComponent = () => {
      val (state, _) = useFetch[List[Item]]("/api/items")

      div(
        cls := "fetch-test",
        state match {
          case FetchState.Success(items) =>
            ul(
              cls := "items",
              items.map(item =>
                li(key_ := item.id, cls := "item", item.name),
              ),
            )
          case FetchState.Loading() =>
            div(cls := "loading", "Loading...")
          case FetchState.Error(error) =>
            div(cls := "error", error.getMessage)
          case FetchState.Idle() =>
            div(cls := "idle", "Idle")
        },
      )
    }

    render(TestComponent <> (), container)

    eventually {
      val items = container.querySelectorAll(".item")
      items.length shouldBe 3
      items(0).textContent shouldBe "First Item"
      items(1).textContent shouldBe "Second Item"
      items(2).textContent shouldBe "Third Item"
    }
  }

  it should "handle HTTP errors (404)" in {
    case class Item(id: Int, name: String) derives JsonDecoder

    val container = getContainer

    def TestComponent = () => {
      val (state, _) = useFetch[List[Item]]("/api/not-found")

      div(
        cls := "fetch-test",
        state match {
          case FetchState.Error(FetchError.HttpError(status, text)) =>
            div(
              cls := "error",
              s"HTTP Error $status: $text",
            )
          case FetchState.Loading() =>
            div(cls := "loading", "Loading...")
          case other =>
            div(cls := "unexpected", other.toString)
        },
      )
    }

    render(TestComponent <> (), container)

    eventually {
      val errorDiv = container.querySelector(".error")

      errorDiv should not be null
      errorDiv.textContent shouldBe "HTTP Error 404: Not Found"
    }
  }
}
