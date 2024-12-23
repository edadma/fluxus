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
          "componentType" -> props.getClass.getSimpleName,
          "props"         -> props.toString,
        ),
      )

      // Create instance for component
      val instance = ComponentInstance(
        componentType = props.getClass.getSimpleName,
        node = comp,
        parent = ComponentInstance.current, // Get current parent instance if any
      )

      // Store instance in node
      comp.instance = Some(instance)

      // Call component function to get rendered node
      val rendered = ComponentInstance.withInstance(instance) {
        component(props)
      }

      instance.rendered = Some(rendered)

      // Create DOM from rendered node
      val compNode = createDOMNode(rendered)

      BatchScheduler.scheduleEffects(instance)

      compNode

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

  // After entire tree is created, find all component instances and run their effects
//  def collectInstances(node: FluxusNode): Seq[ComponentInstance] = {
//    node match {
//      case comp: ComponentNode =>
//        // Get this component's instance first (parent)
//        val thisInstance = comp.instance.toSeq
//        // Then get child instances from rendered tree
//        val childInstances = comp.instance.flatMap(_.rendered).toSeq.flatMap(collectInstances)
//        // Parent first, then children
//        thisInstance ++ childInstances
//      case elem: ElementNode =>
//        elem.children.flatMap(collectInstances)
//      case _ => Nil
//    }
//  }

//  val instances = collectInstances(root)
//
//  logger.debug(
//    "Running effects for initial render",
//    category = "DOM",
//    Map(
//      "instanceCount" -> instances.size.toString,
//      "instances"     -> instances.map(_.id).mkString(", "),
//    ),
//  )

//  BatchScheduler.handleEffects(instances.toSet)
}
