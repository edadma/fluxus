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

  "createDOM" should "handle a single child element" in {
    val container = getContainer
    val node = ElementNode(
      tag = "div",
      attrs = Map("class" -> "parent"),
      events = Map(),
      children = Vector(
        ElementNode(
          tag = "span",
          attrs = Map("class" -> "child"),
          events = Map(),
          children = Vector(),
          parent = None,
          domNode = None,
          key = None,
        ),
      ),
      parent = None,
      domNode = None,
      key = None,
    )

    createDOM(node, container)

    // Verify DOM structure
    val parentDiv = container.firstChild.asInstanceOf[dom.Element]
    println(js.JSON.stringify(parentDiv, null, 2))
//    parentDiv.tagName.toLowerCase shouldBe "div"
//    parentDiv.getAttribute("class") shouldBe "parent"
//
//    val childSpan = parentDiv.firstChild.asInstanceOf[dom.Element]
//    childSpan.tagName.toLowerCase shouldBe "span"
//    childSpan.getAttribute("class") shouldBe "child"
  }

//  it should "handle multiple children" in {
//    val container = getContainer
//    val node = ElementNode(
//      tag = "div",
//      attrs = Map(),
//      events = Map(),
//      children = Vector(
//        TextNode("First", None, None),
//        ElementNode(
//          tag = "span",
//          attrs = Map(),
//          events = Map(),
//          children = Vector(),
//          parent = None,
//          domNode = None,
//          key = None,
//        ),
//        TextNode("Last", None, None),
//      ),
//      parent = None,
//      domNode = None,
//      key = None,
//    )
//
//    createDOM(node, container)
//
//    val parentDiv = container.firstChild.asInstanceOf[dom.Element]
//    parentDiv.childNodes.length shouldBe 3
//    parentDiv.childNodes(0).textContent shouldBe "First"
//    parentDiv.childNodes(1).nodeName.toLowerCase shouldBe "span"
//    parentDiv.childNodes(2).textContent shouldBe "Last"
//  }

  it should "handle deeply nested structures" in {
    val container = getContainer
    val node = ElementNode(
      tag = "div",
      attrs = Map("class" -> "level1"),
      events = Map(),
      children = Vector(
        ElementNode(
          tag = "div",
          attrs = Map("class" -> "level2"),
          events = Map(),
          children = Vector(
            ElementNode(
              tag = "div",
              attrs = Map("class" -> "level3"),
              events = Map(),
              children = Vector(
                TextNode("Deeply nested", None, None),
              ),
              parent = None,
              domNode = None,
              key = None,
            ),
          ),
          parent = None,
          domNode = None,
          key = None,
        ),
      ),
      parent = None,
      domNode = None,
      key = None,
    )

    createDOM(node, container)

    val level1 = container.querySelector(".level1")
    val level2 = level1.querySelector(".level2")
    val level3 = level2.querySelector(".level3")

    level3.textContent shouldBe "Deeply nested"
  }
}
