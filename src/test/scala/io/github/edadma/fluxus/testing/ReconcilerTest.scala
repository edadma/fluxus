package io.github.edadma.fluxus.testing

import io.github.edadma.fluxus.*
import io.github.edadma.fluxus.core.{createDOM, reconcile, diff, commit}
import io.github.edadma.fluxus.core.DOMOperation.*
import org.scalajs.dom

class ReconcilerTest extends DOMSpec {
  "TextNode reconciliation" should "generate correct operations and update DOM when text changes" in {
    val container = getContainer

    // Create and mount initial text node
    val oldNode = TextNode("Hello", None, None)
    createDOM(oldNode, container)

    // Verify initial state
    container.textContent shouldBe "Hello"

    // Create new text node with different content
    val newNode = TextNode("World", None, None)

    // Get and verify operations
    val ops = diff(Some(oldNode), Some(newNode))
    ops shouldBe Seq(UpdateText(oldNode, "World"))

    // Commit operations and verify DOM
    ops.foreach(op => commit(op, container))
    container.textContent shouldBe "World"
  }

  it should "generate no operations when text hasn't changed" in {
    val container = getContainer

    // Create and mount initial text node
    val oldNode = TextNode("Hello", None, None)
    createDOM(oldNode, container)

    // Get reference to original DOM node
    val originalDomNode = container.firstChild

    // Create new text node with same content
    val newNode = TextNode("Hello", None, None)

    // Verify no operations generated
    val ops = diff(Some(oldNode), Some(newNode))
    ops shouldBe empty

    // Commit operations and verify DOM unchanged
    ops.foreach(op => commit(op, container))
    container.firstChild shouldBe originalDomNode
    container.textContent shouldBe "Hello"
  }

  it should "generate remove operation for node removal" in {
    val container = getContainer

    // Create and mount initial text node
    val oldNode = TextNode("Hello", None, None)
    createDOM(oldNode, container)

    // Verify initial state
    container.hasChildNodes() shouldBe true

    // Get and verify operations
    val ops = diff(Some(oldNode), None)
    ops shouldBe Seq(RemoveNode(oldNode))

    // Commit operations and verify DOM
    ops.foreach(op => commit(op, container))
    container.hasChildNodes() shouldBe false
  }

  "Element attribute reconciliation" should "generate correct update operations" in {
    val container = getContainer

    val oldNode = ElementNode("div", Map("class" -> "old"), Map(), Vector(), None, None, None)
    val newNode = ElementNode("div", Map("class" -> "new"), Map(), Vector(), None, None, None)

    // Create initial DOM
    createDOM(oldNode, container)

    // Verify initial state
    container.firstChild.asInstanceOf[dom.Element].getAttribute("class") shouldBe "old"

    // Get and verify operations
    val ops = diff(Some(oldNode), Some(newNode))
    ops shouldBe Seq(UpdateProps(oldNode, Set(), Map("class" -> "new")))

    // Commit operations and verify DOM
    ops.foreach(op => commit(op, container))
    container.firstChild.asInstanceOf[dom.Element].getAttribute("class") shouldBe "new"
  }

  "Component reconciliation" should "generate rerender operation when props change" in {
    val container = getContainer

    case class Props(value: String)

    val component = ((props: Props) => TextNode(props.value, None, None)).asInstanceOf[Product => FluxusNode]
    val oldNode   = ComponentNode(component, Props("old"), None)
    val newNode   = ComponentNode(component, Props("new"), None)

    // Create initial DOM
    createDOM(oldNode, container)

    // Verify initial state
    container.textContent shouldBe "old"

    // Get and verify operations
    val ops = diff(Some(oldNode), Some(newNode))
    ops shouldBe Seq(RerenderComponent(oldNode, newNode))

    // Commit operations and verify DOM
    ops.foreach(op => commit(op, container))
    container.textContent shouldBe "new"
  }

  it should "generate no operations when props haven't changed" in {
    val container = getContainer

    case class Props(value: String)

    val component = ((props: Props) => TextNode(props.value, None, None)).asInstanceOf[Product => FluxusNode]
    val oldNode   = ComponentNode(component, Props("same"), None)
    val newNode   = ComponentNode(component, Props("same"), None)

    // Create initial DOM
    createDOM(oldNode, container)

    // Get operations
    val ops = diff(Some(oldNode), Some(newNode))

    // Verify no operations generated
    ops shouldBe empty

    // Verify DOM remains unchanged
    container.textContent shouldBe "same"
  }
}
