package io.github.edadma.fluxus.core.dom

import io.github.edadma.fluxus.core.types._
import io.github.edadma.fluxus.testing.DOMSpec
import org.scalajs.dom.html

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

//  it should "handle click events" in {
//    var clicked = false
//    val handler = (_: org.scalajs.dom.Event) => clicked = true
//
//    val node = ElementNode(
//      tag = "button",
//      props = Map.empty,
//      events = Map("onClick" -> handler),
//      children = Vector.empty,
//      parent = None,
//      domNode = None,
//      key = None,
//    )
//
//    DOMOperations.mount(node, getContainer)
//
//    val button = getContainer.querySelector("button")
//    button should not be null
//
//    // Simulate click
//    val clickEvent = new org.scalajs.dom.Event("click")
//    button.dispatchEvent(clickEvent)
//
//    clicked shouldBe true
//  }
//
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
