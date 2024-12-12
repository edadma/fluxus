package io.github.edadma.fluxus.core

import io.github.edadma.fluxus.{ElementNode, FluxusNode, TextNode}
import io.github.edadma.logger.LoggerFactory
import org.scalajs.dom.{Element, Event, Node, document}

val logger = LoggerFactory.getLogger

def createDOMNode(node: FluxusNode): Node = {
  logger.debug(
    "Creating DOM node",
    category = "DOM",
    opId = 1,
    Map(
      "nodeType"    -> node.getClass.getSimpleName,
      "nodeDetails" -> node.toString,
    ),
  )

  val domNode = node match
    case TextNode(text, _, _) =>
      logger.debug("Creating text node", category = "DOM", opId = 1, Map("text" -> text))
      document.createTextNode(text)

    case ElementNode(tag, attrs, events, _, _, _, _, namespace, _) =>
      logger.debug(
        "Creating element node",
        category = "DOM",
        opId = 1,
        Map(
          "tag"    -> tag,
          "attrs"  -> attrs.toString,
          "events" -> events.toString,
        ),
      )

      // Create element with proper namespace if specified
      val elem = namespace match
        case Some(ns) => document.createElementNS(ns, tag)
        case None     => document.createElement(tag)

      // Set attributes
      attrs.foreach { case (name, value) =>
        logger.debug(
          "Setting attribute",
          category = "DOM",
          opId = 1,
          Map(
            "name"  -> name,
            "value" -> value.toString,
          ),
        )

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
  logger.debug(
    "DOM node created",
    category = "DOM",
    opId = 1,
    Map(
      "domNode" -> domNode.toString,
    ),
  )

  domNode
}

def createDOM(root: FluxusNode, container: Element): Unit = {
  logger.debug(
    "Creating DOM tree",
    category = "DOM",
    opId = 1,
    Map(
      "rootType"      -> root.getClass.getSimpleName,
      "containerType" -> container.nodeName,
    ),
  )

  val dom = createDOMNode(root)

  root match
    case ElementNode(_, _, _, children, _, _, _, _, _) =>
      logger.debug(
        "Processing children",
        category = "DOM",
        opId = 1,
        Map(
          "childCount" -> children.size,
        ),
      )
      children foreach { child =>
        createDOM(child, dom.asInstanceOf[Element])
      }
    case _ =>
      logger.debug("Skipping children for non-element node", category = "DOM", opId = 1)

  logger.debug("Appending to container", category = "DOM", opId = 1)
  container.appendChild(dom)
}
