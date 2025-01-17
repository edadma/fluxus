package io.github.edadma.fluxus.testing

import io.github.edadma.fluxus.*
import io.github.edadma.fluxus.core.{createDOM, createDOMNode}
import org.scalajs.dom
import org.scalajs.dom.Node

import scala.scalajs.js

class DOMTests extends AnyDOMSpec {

  "createDOMNode" should "create a DOM text node with correct content" in {
    val node    = TextNode("Hello world", None, None)
    val domNode = createDOMNode(node)

    domNode.nodeType shouldBe dom.Node.TEXT_NODE
    domNode.textContent shouldBe "Hello world"
    node.domNode shouldBe Some(domNode)
  }

  it should "create an empty div element" in {
    val node    = div()
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
    val node    = div(cls := "test-class", id := "test-id", "data-test" := "test-value")
    val domNode = createDOMNode(node).asInstanceOf[dom.Element]

    domNode.getAttribute("class") shouldBe "test-class"
    domNode.getAttribute("id") shouldBe "test-id"
    domNode.getAttribute("data-test") shouldBe "test-value"
  }

  it should "handle numeric attributes" in {
    val node = input(
      maxLength := 50,
      tabIndex  := 1,
    )

    val domNode = createDOMNode(node).asInstanceOf[dom.Element]

    domNode.getAttribute("maxLength") shouldBe "50"
    domNode.getAttribute("tabIndex") shouldBe "1"
  }

  it should "handle style attributes correctly" in {
    val node = div(
      style := "color: red; font-size: 12px; margin-top: 10px",
    )

    val domNode       = createDOMNode(node).asInstanceOf[dom.Element]
    val computedStyle = domNode.asInstanceOf[dom.html.Element].style

    computedStyle.color shouldBe "red"
    computedStyle.fontSize shouldBe "12px"
    computedStyle.marginTop shouldBe "10px"
  }

  it should "handle data attributes with dashes" in {
    val node = div(
      "data-test-id"   := "123",
      "data-user-role" := "admin",
      "aria-label"     := "test label",
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

    val node    = button(onClick := (() => clicked = true))
    val domNode = createDOMNode(node).asInstanceOf[dom.Element]

    // Simulate a click event
    click(domNode)

    clicked shouldBe true
  }

  it should "pass the event object to event handlers" in {
    var receivedEvent: dom.Event = null
    val node                     = button(onClick := ((e: dom.Event) => receivedEvent = e))
    val domNode                  = createDOMNode(node).asInstanceOf[dom.Element]

    // Simulate a click event
    val clickEvent = click(domNode)

    receivedEvent should not be null
    receivedEvent shouldBe clickEvent
  }

  it should "handle a single child element" in {
    val container = getContainer
    logger.debug(
      "Test container",
      category = "Test",
      opId = 1,
      Map(
        "container"     -> container.toString,
        "containerType" -> container.nodeName,
      ),
    )

    val node = div(
      cls := "parent",
      span(
        cls := "child",
      ),
    )

    createDOM(node, container)
    logger.debug(
      "Before assertions",
      category = "Test",
      opId = 1,
      Map(
        "containerFirstChild" -> Option(container.firstChild).map(_.toString).getOrElse("null"),
        "containerChildNodes" -> container.childNodes.length,
      ),
    )

    // Get the parent div and log its state
    val parentDiv = container.firstChild.asInstanceOf[dom.Element]

    logger.debug(
      "Parent div details",
      category = "Test",
      opId = 1,
      Map(
        "parentDiv"        -> parentDiv.toString,
        "tagName"          -> Option(parentDiv.tagName).map(_.toLowerCase).getOrElse("null"),
        "class"            -> Option(parentDiv.getAttribute("class")).getOrElse("null"),
        "childNodesLength" -> parentDiv.childNodes.length,
        "firstChild"       -> Option(parentDiv.firstChild).map(_.toString).getOrElse("null"),
      ),
    )

    // More specific debugging
    if (parentDiv.hasChildNodes()) {
      val childSpan = parentDiv.firstChild.asInstanceOf[dom.Element]
      logger.debug(
        "Child span state",
        category = "Test",
        opId = 1,
        Map(
          "childSpan" -> childSpan.toString,
          "tagName"   -> childSpan.tagName,
          "className" -> childSpan.getAttribute("class"),
        ),
      )
    } else {
      logger.warn("No child nodes found", category = "Test", opId = 1)
    }

    // Original assertions with more context
    Option(parentDiv.tagName).map(_.toLowerCase) shouldBe Some("div")
    Option(parentDiv.getAttribute("class")) shouldBe Some("parent")

    val childSpan = parentDiv.firstChild.asInstanceOf[dom.Element]
    logger.debug(
      "Child span details",
      category = "Test",
      opId = 1,
      Map(
        "childSpan" -> childSpan.toString,
        "tagName"   -> Option(childSpan.tagName).map(_.toLowerCase).getOrElse("null"),
        "class"     -> Option(childSpan.getAttribute("class")).getOrElse("null"),
      ),
    )

    Option(childSpan.tagName).map(_.toLowerCase) shouldBe Some("span")
    Option(childSpan.getAttribute("class")) shouldBe Some("child")
  }

  it should "handle multiple children" in {
    val container = getContainer
    val node = div(
      "First",
      span(),
      "Last",
    )

    createDOM(node, container)

    val parentDiv = container.firstChild.asInstanceOf[dom.Element]
    parentDiv.childNodes.length shouldBe 3
    parentDiv.childNodes(0).textContent shouldBe "First"
    parentDiv.childNodes(1).nodeName.toLowerCase shouldBe "span"
    parentDiv.childNodes(2).textContent shouldBe "Last"
  }

  it should "handle deeply nested structures" in {
    val container = getContainer
    val node = div(
      cls := "level1",
      div(
        cls := "level2",
        div(
          cls := "level3",
          "Deeply nested",
        ),
      ),
    )

    createDOM(node, container)

    val level1 = container.querySelector(".level1")
    val level2 = level1.querySelector(".level2")
    val level3 = level2.querySelector(".level3")

    level3.textContent shouldBe "Deeply nested"
  }

  case class NoProps()

  "ComponentNode" should "render a simple functional component" in {
    val container = getContainer

    // Simple function that takes no props
    val SimpleComponent = () =>
      div(
        cls := "test-component",
        "Hello from component",
      )

    val node = SimpleComponent <> ()

    createDOM(node, container)

    logger.debug(
      "Component test state",
      category = "Test",
      opId = 1,
      Map(
        "containerChildCount" -> container.childNodes.length,
        "containerInnerHTML"  -> container.innerHTML,
        "firstChildType"      -> Option(container.firstChild).map(_.nodeType).getOrElse("null"),
        "firstChildTagName"   -> Option(container.firstChild).map(_.nodeName).getOrElse("null"),
        "textContent"         -> Option(container.firstChild).map(_.textContent).getOrElse("null"),
      ),
    )

    val componentDiv = container.firstChild.asInstanceOf[dom.Element]
    componentDiv.tagName.toLowerCase shouldBe "div"
    componentDiv.getAttribute("class") shouldBe "test-component"
    componentDiv.textContent shouldBe "Hello from component"
  }

  it should "render a component that returns text" in {
    val container = getContainer

    val TextComponent = () => TextNode("Just text", None, None)

    val node = TextComponent <> ()

    createDOM(node, container)

    logger.debug(
      "Text component test",
      category = "Test",
      opId = 1,
      Map(
        "containerChildCount" -> container.childNodes.length,
        "containerInnerHTML"  -> container.innerHTML,
        "firstChildType"      -> Option(container.firstChild).map(_.nodeType).getOrElse("null"),
        "textContent"         -> Option(container.firstChild).map(_.textContent).getOrElse("null"),
      ),
    )

    container.textContent shouldBe "Just text"
  }

  "ComponentNode" should "handle multiple levels of component nesting" in {
    val container = getContainer

    // Inner component - returns just a span with text
    val InnerComponent: () => FluxusNode = () =>
      span("Inner content")

    // Middle component - wraps inner component in a div
    val MiddleComponent: () => FluxusNode = () =>
      div(
        cls := "middle",
        p("Middle content"),
        InnerComponent <> (),
      )

    // Outer component - uses middle component
    val OuterComponent: () => FluxusNode = () =>
      div(
        cls := "outer",
        h1("Outer content"),
        MiddleComponent <> (),
      )

    // Create and render the outer component
    val node = OuterComponent <> ()

    createDOM(node, container)

    // Verify the complete structure
    val outerDiv = container.firstChild.asInstanceOf[dom.Element]
    outerDiv.tagName.toLowerCase shouldBe "div"
    outerDiv.getAttribute("class") shouldBe "outer"

    // First child should be h1 with "Outer content"
    val h1Elem = outerDiv.firstChild.asInstanceOf[dom.Element]
    h1Elem.tagName.toLowerCase shouldBe "h1"
    h1Elem.textContent shouldBe "Outer content"

    // Second child should be middle div
    val middleDiv = h1Elem.nextSibling.asInstanceOf[dom.Element]
    middleDiv.tagName.toLowerCase shouldBe "div"
    middleDiv.getAttribute("class") shouldBe "middle"

    // First child of middle should be p with "Middle content"
    val pElem = middleDiv.firstChild.asInstanceOf[dom.Element]
    pElem.tagName.toLowerCase shouldBe "p"
    pElem.textContent shouldBe "Middle content"

    // Second child should be span from inner component
    val spanElem = pElem.nextSibling.asInstanceOf[dom.Element]
    spanElem.tagName.toLowerCase shouldBe "span"
    spanElem.textContent shouldBe "Inner content"
  }

  "ElementNode" should "render a list of text items" in {
    val container = getContainer

    // Create an unordered list with array of list items
    val items = Vector("First", "Second", "Third")
    val node = ul(
      items.map(text => li(text)), // Each item wrapped in li
    )

    createDOM(node, container)

    // Get the ul element
    val ulElement = container.firstChild.asInstanceOf[dom.Element]
    ulElement.tagName.toLowerCase shouldBe "ul"

    // Verify list items
    val liElements = ulElement.childNodes
    liElements.length shouldBe 3

    // Check each li element
    for {
      i <- 0 until 3
      li = liElements(i).asInstanceOf[dom.Element]
    } {
      li.tagName.toLowerCase shouldBe "li"
      li.textContent shouldBe items(i)
    }
  }
}
