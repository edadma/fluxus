package io.github.edadma.fluxus.core

import io.github.edadma.fluxus.{ElementNode, FluxusNode, TextNode}
import org.scalajs.dom.{Element, Node, document, Event}

def createDOMNode(node: FluxusNode): Node = {
  val domNode = node match
    case TextNode(text, _, _)                                      => document.createTextNode(text)
    case ElementNode(tag, attrs, events, _, _, _, _, namespace, _) =>
      // Create element with proper namespace if specified
      val elem =
        namespace match
          case Some(ns) => document.createElementNS(ns, tag)
          case None     => document.createElement(tag)
      //    case _: ComponentNode                            =>

      // Set attributes based on their type
      attrs.foreach { case (name, value) =>
        value match
          case b: Boolean =>
            // Only set attribute if true, remove if false
            if (b) elem.setAttribute(name, "")
            else elem.removeAttribute(name)

          case null | None =>
            // Remove attribute for null/None values
            elem.removeAttribute(name)

          case Some(v) =>
            // Handle Option values
            elem.setAttribute(name, v.toString)

          case v =>
            // Handle all other values by converting to string
            elem.setAttribute(name, v.toString)
      }

      // Handle events by converting names and attaching listeners
      events.foreach { case (eventName, handler) =>
        // Convert "onClick" to "click" etc
        val domEventName = eventName.toLowerCase match {
          case name if name.startsWith("on") => name.substring(2)
          case name                          => name
        }

        // Attach event listener based on handler type
        handler match {
          case f: (() => _) =>
            elem.addEventListener(domEventName, (e: Event) => f())
          case f: ((_) => _) =>
            elem.addEventListener(domEventName, (e: Event) => f.asInstanceOf[Event => ?](e))
        }
      }

      elem
  // Store the created DOM node
  node.domNode = Some(domNode.asInstanceOf[Node])
  domNode.asInstanceOf[Node]
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
