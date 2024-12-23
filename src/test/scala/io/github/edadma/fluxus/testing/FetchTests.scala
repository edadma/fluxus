package io.github.edadma.fluxus.testing

import io.github.edadma.fluxus.*
import io.github.edadma.fluxus.core.*
import org.scalajs.dom
import zio.json.*
import scala.scalajs.js.JSConverters.*
import scala.scalajs.js

class FetchTests extends AsyncDOMSpec {
  // Set up mock server before tests
  val mockServer = MockServer(
    MockEndpoint(
      path = "/api/items",
      response = Right("""[
        {"id": 1, "name": "First Item"},
        {"id": 2, "name": "Second Item"},
        {"id": 3, "name": "Third Item"}
      ]"""),
    ),
  )

  // Override fetch in jsdom environment
  js.Dynamic.global.fetch = (url: String, init: dom.RequestInit) =>
    mockServer.handle(url, init).toJSPromise

  "useFetch" should "successfully fetch and render a list of items" in {
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

    createDOM(TestComponent <> (), container)

    eventually {
      val items = container.querySelectorAll(".item")
      items.length shouldBe 3
      items(0).textContent shouldBe "First Item"
      items(1).textContent shouldBe "Second Item"
      items(2).textContent shouldBe "Third Item"
    }
  }
}
