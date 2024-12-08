package io.github.edadma.fluxus.core

import io.github.edadma.fluxus.{ElementNode, FluxusNode, TextNode}
import org.scalajs.dom.{Element, Node, document}

def createDOMNode(node: FluxusNode): Node = {
  val dom = node match
    case TextNode(text, _, _) =>
      document.createTextNode(text)
    case ElementNode(tag, props, events, _, _, _, _, _, _) =>
//    case _: ComponentNode                            =>

  // Store the created DOM node
  node.domNode = Some(dom.asInstanceOf[Node])
  dom.asInstanceOf[Node]
}

def createDOM(root: FluxusNode, container: Element): Unit =
  val dom = createDOMNode(root)

  root match
    case ElementNode(_, _, _, children, _, _, _, _, _) =>
      children foreach { child =>
        createDOM(child, dom.asInstanceOf[Element])
      }
    case _ => // Nothing to do for text/component nodes
  container.appendChild(dom)
