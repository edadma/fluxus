package io.github.edadma.fluxus.testing

import io.github.edadma.fluxus.{ElementNode, TextNode}
import io.github.edadma.fluxus.core.{createDOM, createDOMNode}
import org.scalajs.dom
import org.scalajs.dom.Node

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
}
