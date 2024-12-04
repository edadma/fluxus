package io.github.edadma.fluxus.core.dom

import io.github.edadma.fluxus.core.types.*
import io.github.edadma.fluxus.core.hooks.Hooks.useState
import io.github.edadma.fluxus.testing.DOMSpec
import org.scalajs.dom
import org.scalajs.dom.html

import scala.scalajs.js

class DOMOperationsTest extends DOMSpec {

  "DOMOperations" should "create a basic element" in {
    val node = ElementNode(
      tag = "div",
      props = Map("class" -> "test"),
      events = Map.empty,
      children = Vector.empty,
      parent = None,
      domNode = None,
      key = None,
    )

    DOMOperations.mount(node, getContainer)

    val div = getContainer.querySelector("div").asInstanceOf[html.Div]
    div should not be null
    div.className shouldBe "test"
  }

  it should "handle nested elements" in {
    val node = ElementNode(
      tag = "div",
      props = Map.empty,
      events = Map.empty,
      children = Vector(
        ElementNode(
          tag = "span",
          props = Map("class" -> "child"),
          events = Map.empty,
          children = Vector(TextNode("test content", None, None, None)),
          parent = None,
          domNode = None,
          key = None,
        ),
      ),
      parent = None,
      domNode = None,
      key = None,
    )

    DOMOperations.mount(node, getContainer)

    val span = getContainer.querySelector("span").asInstanceOf[html.Span]

    span should not be null
    span.className shouldBe "child"
    span.textContent shouldBe "test content"
  }

  it should "handle text node updates via diffing" in {
    val oldNode = TextNode("old text", None, None, None)
    val newNode = TextNode("new text", None, None, None)

    DOMOperations.mount(oldNode, getContainer)
    getContainer.textContent shouldBe "old text"

    Reconciler.diff(Some(oldNode), Some(newNode), getContainer)
    getContainer.textContent shouldBe "new text"
  }

  it should "handle click events" in {
    var clicked = false
    val handler = (_: dom.Event) => clicked = true

    val node = ElementNode(
      tag = "button",
      props = Map.empty,
      events = Map("onClick" -> handler),
      children = Vector(TextNode("Click me", None, None, None)),
      parent = None,
      domNode = None,
      key = None,
    )

    DOMOperations.mount(node, getContainer)

    val button = getContainer.querySelector("button")
    button should not be null

    // Simulate click
    val clickEvent = dom.document.createEvent("Event")
    clickEvent.asInstanceOf[js.Dynamic].initEvent("click", true, true)
    button.dispatchEvent(clickEvent)
    clicked shouldBe true
  }

  it should "pass event object to handlers" in {
    var receivedEvent: dom.Event = null
    val handler                  = (e: dom.Event) => receivedEvent = e

    val node = ElementNode(
      tag = "button",
      props = Map.empty,
      events = Map("onClick" -> handler),
      children = Vector.empty,
      parent = None,
      domNode = None,
      key = None,
    )

    DOMOperations.mount(node, getContainer)

    val button = getContainer.querySelector("button")
    button should not be null

    val clickEvent = dom.document.createEvent("Event")
    clickEvent.asInstanceOf[js.Dynamic].initEvent("click", true, true)
    button.dispatchEvent(clickEvent)

    receivedEvent should not be null
    receivedEvent.`type` shouldBe "click"
  }

  it should "update DOM after state changes from event" in {
    val initialText = TextNode("Initial", None, None, None)

    val node = ElementNode(
      tag = "div",
      props = Map.empty,
      events = Map.empty,
      children = Vector(
        ElementNode(
          tag = "button",
          props = Map.empty,
          events = Map("onClick" -> ((_: dom.Event) => {
            // Simulate state update by diffing in new content
            val newContent = TextNode("Updated", None, None, None)
            val container  = dom.document.querySelector("span")
            Reconciler.diff(Some(initialText), Some(newContent), container.asInstanceOf[dom.Element])
          })),
          children = Vector.empty,
          parent = None,
          domNode = None,
          key = None,
        ),
        ElementNode(
          tag = "span",
          props = Map.empty,
          events = Map.empty,
          children = Vector(initialText),
          parent = None,
          domNode = None,
          key = None,
        ),
      ),
      parent = None,
      domNode = None,
      key = None,
    )

    DOMOperations.mount(node, getContainer)

    // Verify initial state
    val span = getContainer.querySelector("span")
    span.textContent shouldBe "Initial"

    // Simulate click
    val button     = getContainer.querySelector("button")
    val clickEvent = dom.document.createEvent("Event")
    clickEvent.asInstanceOf[js.Dynamic].initEvent("click", true, true)
    button.dispatchEvent(clickEvent)

    // Verify update
    span.textContent shouldBe "Updated"
  }

  it should "handle state updates in components" in withDebugLogging {
    case class TestProps()

    def TestComponent(props: TestProps): FluxusNode = {
      val (count, setCount) = useState(0)

      ElementNode(
        tag = "div",
        props = Map.empty,
        events = Map.empty,
        children = Vector(
          ElementNode(
            tag = "button",
            props = Map.empty,
            events = Map("onClick" -> ((_: dom.Event) => setCount(count + 1))),
            children = Vector.empty,
            parent = None,
            domNode = None,
            key = None,
          ),
          ElementNode(
            tag = "span",
            props = Map.empty,
            events = Map.empty,
            children = Vector(TextNode(count.toString, None, None, None)),
            parent = None,
            domNode = None,
            key = None,
          ),
        ),
        parent = None,
        domNode = None,
        key = None,
      )
    }

    val component = Component.create(
      render = TestComponent(_),
      props = TestProps(),
      opId = 1,
      name = Some("TestComponent"),
    )

    DOMOperations.mount(component, getContainer)

    val span = getContainer.querySelector("span")
    span.textContent shouldBe "0"

    val button     = getContainer.querySelector("button")
    val clickEvent = dom.document.createEvent("Event")
    clickEvent.asInstanceOf[js.Dynamic].initEvent("click", true, true)
    button.dispatchEvent(clickEvent)

    span.textContent shouldBe "1"
  }

  //  it should "update element attributes" in {
//    val oldNode = ElementNode(
//      tag = "div",
//      props = Map("class" -> "old"),
//      events = Map.empty,
//      children = Vector.empty,
//      parent = None,
//      domNode = None,
//      key = None,
//    )
//
//    val newNode = ElementNode(
//      tag = "div",
//      props = Map("class" -> "new"),
//      events = Map.empty,
//      children = Vector.empty,
//      parent = None,
//      domNode = None,
//      key = None,
//    )
//
//    DOMOperations.mount(oldNode, getContainer)
//    Reconciler.diff(Some(oldNode), Some(newNode), getContainer)
//
//    val div = getContainer.querySelector("div").asInstanceOf[html.Element]
//    div.className shouldBe "new"
//  }
//
//  it should "handle text node updates" in {
//    val oldNode = TextNode("old text", None, None, None)
//    val newNode = TextNode("new text", None, None, None)
//
//    DOMOperations.mount(oldNode, getContainer)
//    Reconciler.diff(Some(oldNode), Some(newNode), getContainer)
//
//    getContainer.textContent shouldBe "new text"
//  }
//
//  it should "remove nodes" in {
//    val node = ElementNode(
//      tag = "div",
//      props = Map.empty,
//      events = Map.empty,
//      children = Vector.empty,
//      parent = None,
//      domNode = None,
//      key = None,
//    )
//
//    DOMOperations.mount(node, getContainer)
//    Reconciler.diff(Some(node), None, getContainer)
//
//    getContainer.childNodes.length shouldBe 0
//  }
}
