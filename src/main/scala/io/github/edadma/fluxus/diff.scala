package io.github.edadma.fluxus

import org.scalajs.dom
import org.scalajs.dom.Event

def diff(oldNode: FluxusNode, newNode: FluxusNode, parent: dom.Node): Unit = {
  FluxusLogger.Render.domUpdate(
    "diff",
    s"Comparing nodes: ${oldNode.getClass.getSimpleName} -> ${newNode.getClass.getSimpleName}",
  )

  // If the nodes are the same, do nothing
  if (oldNode eq newNode) {
    FluxusLogger.Render.domUpdate("diff", "Nodes are identical - reusing")
    newNode.domNode = oldNode.domNode
    return
  }

  (oldNode, newNode) match {
    case (oldTextNode: TextNode, newTextNode: TextNode) =>
      if (oldTextNode.text != newTextNode.text) {
        FluxusLogger.Render.domUpdate("text", s"Updating text: ${oldTextNode.text} -> ${newTextNode.text}")
        val textNode = oldTextNode.domNode.get.asInstanceOf[dom.Text]
        textNode.data = newTextNode.text
        newTextNode.domNode = Some(textNode)
      } else {
        newTextNode.domNode = oldTextNode.domNode
      }

    case (oldElemNode: ElementNode, newElemNode: ElementNode) =>
      if (oldElemNode.tag != newElemNode.tag) {
        FluxusLogger.Render.domUpdate("element", s"Replacing element: ${oldElemNode.tag} -> ${newElemNode.tag}")
        val newDomNode = renderDomNode(newElemNode)
        parent.replaceChild(newDomNode, oldElemNode.domNode.get)
        newElemNode.domNode = Some(newDomNode)
      } else {
        // Update attributes and events
        val domElement = oldElemNode.domNode.get.asInstanceOf[dom.Element]
        updateAttributes(domElement, oldElemNode.attributes, newElemNode.attributes)
        updateEvents(domElement, oldElemNode.events, newElemNode.events, oldElemNode, newElemNode)
        newElemNode.domNode = Some(domElement)

        // Diff children
        diffChildren(oldElemNode.children, newElemNode.children, domElement)
      }

    case (oldComponentNode: ComponentNode, newComponentNode: ComponentNode) =>
      FluxusLogger.State.effect(
        "diff",
        s"Component match: ${newComponentNode.componentFunction.getClass.getSimpleName}",
      )

      newComponentNode.instance = oldComponentNode.instance
      val instance = newComponentNode.instance.getOrElse {
        throw new IllegalStateException("Component instance is None")
      }

      FluxusLogger.State.effect(
        "diff",
        s"Component state: needsRender=${instance.needsRender}, effectsCount=${instance.effects.size}",
      )

      instance.props = newComponentNode.props

      if (instance.needsRender || oldComponentNode.props != newComponentNode.props) {
        FluxusLogger.State.effect("diff", "Component needs update")

        RenderContext.push(instance)
        instance.resetHooks()
        val childVNode = instance.renderFunction(instance.props)
        RenderContext.pop()

        val oldChildVNode = instance.renderedVNode.getOrElse {
          throw new IllegalStateException("ComponentInstance.renderedVNode is None")
        }

        FluxusLogger.State.effect("diff", s"Pre-child diff, effects: ${instance.effects.size}")
        diff(oldChildVNode, childVNode, parent)

        instance.renderedVNode = Some(childVNode)
        newComponentNode.domNode = childVNode.domNode

        // Execute effects if there are any
        if (instance.effects.nonEmpty) {
          FluxusLogger.State.effect("diff", s"Executing ${instance.effects.size} effects")
          RenderContext.push(instance)
          instance.effects.foreach { effect =>
            FluxusLogger.State.effect("diff", "Running effect")
            effect()
          }
          RenderContext.pop()
          instance.effects.clear()
        }
      } else {
        FluxusLogger.State.effect("diff", "Component unchanged")
        newComponentNode.domNode = oldComponentNode.domNode
      }
    case _ =>
      FluxusLogger.Render.domUpdate("diff", "Node type mismatch - replacing")
      val oldDomNode = oldNode.domNode.getOrElse {
        throw new IllegalStateException("oldNode.domNode is None in default case")
      }
      val newDomNode = renderDomNode(newNode)
      parent.replaceChild(newDomNode, oldDomNode)
      newNode.domNode = Some(newDomNode)
  }
}

def diffChildren(oldChildren: List[FluxusNode], newChildren: List[FluxusNode], parent: dom.Element): Unit = {
  val maxLength = Math.max(oldChildren.length, newChildren.length)
  for (i <- 0 until maxLength) {
    (oldChildren.lift(i), newChildren.lift(i)) match {
      case (Some(oldChild), Some(newChild)) =>
        if (oldChild eq newChild) {
          newChild.domNode = oldChild.domNode
        } else {
          diff(oldChild, newChild, parent)
        }

      case (None, Some(newChild)) =>
        FluxusLogger.Render.domUpdate("children", "Adding new child")
        val newDomNode = renderDomNode(newChild)
        parent.appendChild(newDomNode)
        newChild.domNode = Some(newDomNode)

      case (Some(oldChild), None) =>
        FluxusLogger.Render.domUpdate("children", "Removing old child")
        oldChild.domNode match {
          case Some(domNode) => parent.removeChild(domNode)
          case None          => FluxusLogger.Render.domUpdate("children", "Warning: oldChild.domNode is None")
        }

      case (None, None) => // Do nothing
    }
  }
}

def updateAttributes(domElement: dom.Element, oldAttrs: Map[String, String], newAttrs: Map[String, String]): Unit = {
  // Remove old attributes
  val removedAttrs = oldAttrs.keys.filterNot(newAttrs.contains)
  if (removedAttrs.nonEmpty) {
    FluxusLogger.Render.domUpdate("attributes", s"Removing attributes: ${removedAttrs.mkString(", ")}")
    removedAttrs.foreach(attr => domElement.removeAttribute(attr))
  }

  // Update and add new attributes
  newAttrs.foreach { case (attr, value) =>
    if (!oldAttrs.get(attr).contains(value)) {
      FluxusLogger.Render.domUpdate("attributes", s"Setting attribute: $attr = $value")
      domElement.setAttribute(attr, value)
    }
  }
}

def updateEvents(
    domElement: dom.Element,
    oldEvents: Map[String, Event => Unit],
    newEvents: Map[String, Event => Unit],
    oldVnode: ElementNode,
    newVnode: ElementNode,
): Unit = {
  FluxusLogger.Events.debug(
    s"Starting event update for ${domElement.tagName}",
    Map(
      "oldEventsCount"   -> oldEvents.size,
      "newEventsCount"   -> newEvents.size,
      "oldWrappersCount" -> oldVnode.eventListenerWrappers.size,
      "oldHandlerHashes" -> oldEvents.map { case (k, v) => s"$k:${v.hashCode()}" }.mkString(","),
      "newHandlerHashes" -> newEvents.map { case (k, v) => s"$k:${v.hashCode()}" }.mkString(","),
    ),
  )

  // Transfer existing wrappers to new vnode to maintain references
  val oldWrapperCount = oldVnode.eventListenerWrappers.size
  newVnode.eventListenerWrappers.clear()
  oldVnode.eventListenerWrappers.foreach { case (name, wrapper) =>
    newVnode.eventListenerWrappers(name) = wrapper
  }

  FluxusLogger.Events.debug(
    "Transferred event wrappers",
    Map(
      "fromCount"     -> oldWrapperCount,
      "toCount"       -> newVnode.eventListenerWrappers.size,
      "wrapperHashes" -> newVnode.eventListenerWrappers.map { case (k, v) => s"$k:${v._1.hashCode()}" }.mkString(","),
    ),
  )

  // First handle removals
  val eventsToRemove = oldEvents.keySet.diff(newEvents.keySet)
  FluxusLogger.Events.debug(s"Events to remove: ${eventsToRemove.mkString(", ")}")

  eventsToRemove.foreach { eventName =>
    val jsEventName = eventName.stripPrefix("on").toLowerCase
    newVnode.eventListenerWrappers.get(eventName).foreach { case (wrapper, _) =>
      FluxusLogger.Events.debug(
        s"Removing event listener: $eventName",
        Map(
          "wrapperHash" -> wrapper.hashCode(),
          "elementTag"  -> domElement.tagName,
        ),
      )
      domElement.removeEventListener(jsEventName, wrapper)
      newVnode.eventListenerWrappers.remove(eventName)
      FluxusLogger.Events.cleanup("element", 1)
    }
  }

  // Then handle new or changed events
  newEvents.foreach { case (eventName, handler) =>
    val jsEventName = eventName.stripPrefix("on").toLowerCase

    if (!newVnode.eventListenerWrappers.contains(eventName)) {
      // Create new handler reference and wrapper
      val handlerRef                 = HandlerRef(handler)
      val wrapper: dom.Event => Unit = (e: dom.Event) => handlerRef.handler(e)

      FluxusLogger.Events.debug(
        s"Adding new event listener: $eventName",
        Map(
          "newWrapperHash" -> wrapper.hashCode(),
          "elementTag"     -> domElement.tagName,
        ),
      )
      domElement.addEventListener(jsEventName, wrapper)
      newVnode.eventListenerWrappers(eventName) = (wrapper, handlerRef)
      FluxusLogger.Events.add("element", jsEventName)
    } else {
      // Just update the handler reference
      val (wrapper, handlerRef) = newVnode.eventListenerWrappers(eventName)
      val oldHandler            = handlerRef.handler
      handlerRef.handler = handler
      FluxusLogger.Events.debug(
        s"Updated handler reference: $eventName",
        Map(
          "wrapperHash"    -> wrapper.hashCode(),
          "elementTag"     -> domElement.tagName,
          "oldHandlerHash" -> oldHandler.hashCode(),
          "newHandlerHash" -> handler.hashCode(),
        ),
      )
    }
  }

  FluxusLogger.Events.debug(
    "Event update complete",
    Map(
      "elementTag"        -> domElement.tagName,
      "finalWrapperCount" -> newVnode.eventListenerWrappers.size,
      "finalWrapperHashes" -> newVnode.eventListenerWrappers.map { case (k, v) => s"$k:${v._1.hashCode()}" }.mkString(
        ",",
      ),
    ),
  )
}

def renderDomNode(vnode: FluxusNode): dom.Node = vnode match {
  case TextNode(text) =>
    FluxusLogger.Render.domUpdate("create", s"Creating text node: $text")
    val textNode = dom.document.createTextNode(text)
    vnode.domNode = Some(textNode)
    textNode

  case ElementNode(tag, attributes, events, children) =>
    FluxusLogger.Render.domUpdate("create", s"Creating element: $tag")
    val element = dom.document.createElement(tag)

    if (attributes.nonEmpty) {
      FluxusLogger.Render.domUpdate("attributes", s"Setting initial attributes: ${attributes.keys.mkString(", ")}")
    }
    attributes.foreach { case (name, value) =>
      element.setAttribute(name, value)
    }

    // Initialize eventListenerWrappers
    val elementNode = vnode.asInstanceOf[ElementNode]
    elementNode.eventListenerWrappers.clear()

    if (events.nonEmpty) {
      FluxusLogger.Render.domUpdate("events", s"Adding initial events: ${events.keys.mkString(", ")}")
    }
    events.foreach { case (eventName, handler) =>
      val jsEventName                = eventName.stripPrefix("on").toLowerCase
      val handlerRef                 = HandlerRef(handler)
      val wrapper: dom.Event => Unit = (e: dom.Event) => handlerRef.handler(e)
      element.addEventListener(jsEventName, wrapper)
      elementNode.eventListenerWrappers += (eventName -> (wrapper, handlerRef))
    }

    children.foreach { child =>
      val childDomNode = renderDomNode(child)
      element.appendChild(childDomNode)
    }

    vnode.domNode = Some(element)
    element
  case componentNode @ ComponentNode(_, componentFunction, props, instanceOpt) =>
    FluxusLogger.Render.component(componentFunction.getClass.getSimpleName)

    // Create a new ComponentInstance or use existing one
    val instance = instanceOpt.getOrElse {
      val newInstance = ComponentInstance(componentFunction, props)
      componentNode.instance = Some(newInstance)
      newInstance
    }

    // Clear any existing effects before rendering
    instance.effects.clear()

    RenderContext.push(instance)
    instance.resetHooks()
    val childVNode = componentFunction(props)
    RenderContext.pop()

    instance.renderedVNode = Some(childVNode)

    val domNode = renderDomNode(childVNode)
    vnode.domNode = Some(domNode)
    instance.renderedVNode.get.domNode = Some(domNode)

    FluxusLogger.State.effect(componentFunction.getClass.getSimpleName, "Executing initial effects")
    instance.effects.foreach(effect => effect())
    instance.effects.clear()

    domNode
}
