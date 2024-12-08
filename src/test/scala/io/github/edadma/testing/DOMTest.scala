package io.github.edadma.fluxus.core

import io.github.edadma.fluxus.TextNode
import io.github.edadma.testing.DOMSpec
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

  "createDOM" should "attach a text node to container" in {
    val container = getContainer
    val node      = TextNode("Hello world", None, None)

    createDOM(node, container)

    container.textContent shouldBe "Hello world"
    container.firstChild shouldBe node.domNode.get
  }
}
