package io.github.edadma.fluxus.core

import io.github.edadma.fluxus.{ComponentNode, ElementNode, FluxusNode, TextNode, logger}
import org.scalajs.dom
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

    case ElementNode(tag, attrs, events, children, _, _, namespace, _, _) =>
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
      attrs.foreach {
        case ("=checked", value: Boolean)      => elem.asInstanceOf[dom.html.Input].checked = value
        case ("=value", value)                 => elem.asInstanceOf[dom.html.Input].value = value.toString
        case ("=selected", value: Boolean)     => elem.asInstanceOf[dom.html.Option].selected = value
        case (name, _) if name.startsWith("=") => sys.error(s"boolean value was expected for ${name drop 1}")
        case (name, value) =>
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
            case true        => elem.setAttribute(name, "true")
            case false       =>
            case null | None =>
            case Some(v)     => elem.setAttribute(name, v.toString)
            case v           => elem.setAttribute(name, v.toString)
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

    case comp @ ComponentNode(component, props, _, _, _, _) =>
      logger.debug(
        "Creating component node",
        category = "DOM",
        opId = 1,
        Map(
          "props" -> props.toString,
        ),
      )

      // Create instance for component
      val instance = ComponentInstance(
        componentType = props.getClass.getSimpleName,
        node = comp,
      )

      // Store instance in node
      comp.instance = Some(instance)

      // Call component function to get rendered node
      val rendered = ComponentInstance.withInstance(instance) {
        component(props)
      }

      instance.rendered = Some(rendered)

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

private def setAttributeOrProperty(elem: dom.Element, name: String, value: Any): Unit = {
  if (name.startsWith("=")) {
    // Property binding
    val propName = name.substring(1) // Remove "=" prefix
    propName match {
      case "checked"  => elem.asInstanceOf[dom.html.Input].checked = value.asInstanceOf[Boolean]
      case "value"    => elem.asInstanceOf[dom.html.Input].value = value.toString
      case "selected" => elem.asInstanceOf[dom.html.Option].selected = value.asInstanceOf[Boolean]
      // Add other property bindings as needed
    }
  } else {
    // Regular attribute binding
    setDOMAttribute(elem, name, value)
  }
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
