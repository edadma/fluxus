package io.github.edadma.fluxus.core

import io.github.edadma.fluxus.{logger, ElementNode, FluxusNode, TextNode, ComponentNode}
import org.scalajs.dom.{Element, Event, Node, document}

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

    case ElementNode(tag, attrs, events, children, _, _, namespace, _) =>
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

        elem.addEventListener(domEventName, handler)
      }

      children foreach { child =>
        createDOM(child, elem)
      }

      elem

    case ComponentNode(component, props, _, _) =>
      logger.debug(
        "Creating component node",
        category = "DOM",
        opId = 1,
        Map(
          "props" -> props.toString,
        ),
      )
      // Call component function to get rendered node
      val rendered = component(props)
      // Create DOM from rendered node
      createDOMNode(rendered)

  // Store the created DOM node
  node.domNode = Some(domNode)
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

  logger.debug("Appending to container", category = "DOM", opId = 1)
  container.appendChild(dom)
}
