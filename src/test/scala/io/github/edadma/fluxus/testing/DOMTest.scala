package io.github.edadma.fluxus.testing

import io.github.edadma.fluxus.{ElementNode, TextNode}
import io.github.edadma.fluxus.core.{createDOM, createDOMNode}
import org.scalajs.dom
import org.scalajs.dom.Node

import scala.scalajs.js

class DOMTest extends DOMSpec {

  "createDOMNode" should "create a DOM text node with correct content" in {
    val node    = TextNode("Hello world", None, None)
    val domNode = createDOMNode(node)

    domNode.nodeType shouldBe dom.Node.TEXT_NODE
    domNode.textContent shouldBe "Hello world"
    node.domNode shouldBe Some(domNode)
  }

  it should "create an empty div element" in {
    val node = ElementNode(
      tag = "div",
      attrs = Map(),
      events = Map(),
      children = Vector(),
      parent = None,
      domNode = None,
      key = None,
    )
    val domNode = createDOMNode(node)

    domNode.nodeType shouldBe dom.Node.ELEMENT_NODE
    domNode.nodeName.toLowerCase shouldBe "div"
    node.domNode shouldBe Some(domNode)
  }

  "createDOM" should "attach a text node to container" in {
    val container = getContainer
    val node      = TextNode("Hello world", None, None)

    createDOM(node, container)

    container.textContent shouldBe "Hello world"
    container.firstChild shouldBe node.domNode.get
  }

  "createDOMNode" should "set string attributes on elements" in {
    val node = ElementNode(
      tag = "div",
      attrs = Map(
        "class"     -> "test-class",
        "id"        -> "test-id",
        "data-test" -> "test-value",
      ),
      events = Map(),
      children = Vector(),
      parent = None,
      domNode = None,
      key = None,
    )

    val domNode = createDOMNode(node).asInstanceOf[dom.Element]

    domNode.getAttribute("class") shouldBe "test-class"
    domNode.getAttribute("id") shouldBe "test-id"
    domNode.getAttribute("data-test") shouldBe "test-value"
  }

  it should "handle boolean attributes correctly" in {
    val node = ElementNode(
      tag = "input",
      attrs = Map(
        "disabled" -> true,
        "checked"  -> false,
      ),
      events = Map(),
      children = Vector(),
      parent = None,
      domNode = None,
      key = None,
    )

    val domNode = createDOMNode(node).asInstanceOf[dom.Element]

    domNode.hasAttribute("disabled") shouldBe true
    domNode.hasAttribute("checked") shouldBe false
  }

  it should "handle numeric attributes" in {
    val node = ElementNode(
      tag = "input",
      attrs = Map(
        "maxLength" -> 50,
        "tabIndex"  -> 1,
      ),
      events = Map(),
      children = Vector(),
      parent = None,
      domNode = None,
      key = None,
    )

    val domNode = createDOMNode(node).asInstanceOf[dom.Element]

    domNode.getAttribute("maxLength") shouldBe "50"
    domNode.getAttribute("tabIndex") shouldBe "1"
  }

  it should "handle style attributes correctly" in {
    val node = ElementNode(
      tag = "div",
      attrs = Map(
        "style" -> "color: red; font-size: 12px; margin-top: 10px",
      ),
      events = Map(),
      children = Vector(),
      parent = None,
      domNode = None,
      key = None,
    )

    val domNode       = createDOMNode(node).asInstanceOf[dom.Element]
    val computedStyle = domNode.asInstanceOf[dom.html.Element].style

    computedStyle.color shouldBe "red"
    computedStyle.fontSize shouldBe "12px"
    computedStyle.marginTop shouldBe "10px"
  }

  it should "handle data attributes with dashes" in {
    val node = ElementNode(
      tag = "div",
      attrs = Map(
        "data-test-id"   -> "123",
        "data-user-role" -> "admin",
        "aria-label"     -> "test label", // Also testing aria attributes while we're at it
      ),
      events = Map(),
      children = Vector(),
      parent = None,
      domNode = None,
      key = None,
    )

    val domNode = createDOMNode(node).asInstanceOf[dom.Element]

    domNode.getAttribute("data-test-id") shouldBe "123"
    domNode.getAttribute("data-user-role") shouldBe "admin"
    domNode.getAttribute("aria-label") shouldBe "test label"

    // Also verify we can access via dataset API
    domNode.asInstanceOf[dom.html.Element].dataset.get("testId") shouldBe defined
    domNode.asInstanceOf[dom.html.Element].dataset.get("userRole") shouldBe defined
  }

  "createDOMNode" should "attach click event handlers to elements" in {
    var clicked = false

    val node = ElementNode(
      tag = "button",
      attrs = Map(),
      events = Map(
        "onClick" -> (() => clicked = true),
      ),
      children = Vector(),
      parent = None,
      domNode = None,
      key = None,
    )

    val domNode = createDOMNode(node).asInstanceOf[dom.Element]

    // Simulate a click event
    val clickEvent = dom.document.createEvent("Event")

    clickEvent.asInstanceOf[js.Dynamic].initEvent("click", true, true)
    domNode.dispatchEvent(clickEvent)

    clicked shouldBe true
  }

  it should "pass the event object to event handlers" in {
    var receivedEvent: dom.Event = null

    val node = ElementNode(
      tag = "button",
      attrs = Map(),
      events = Map(
        "onClick" -> ((e: dom.Event) => receivedEvent = e),
      ),
      children = Vector(),
      parent = None,
      domNode = None,
      key = None,
    )

    val domNode = createDOMNode(node).asInstanceOf[dom.Element]

    // Simulate a click event
    val clickEvent = dom.document.createEvent("Event")

    clickEvent.asInstanceOf[js.Dynamic].initEvent("click", true, true)
    domNode.dispatchEvent(clickEvent)

    receivedEvent should not be null
    receivedEvent shouldBe clickEvent
  }
}
