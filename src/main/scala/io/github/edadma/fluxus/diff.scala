package io.github.edadma.fluxus

import org.scalajs.dom

def diff(oldNode: FluxusNode, newNode: FluxusNode, parent: dom.Node): Unit = {
  // If the nodes are the same, do nothing
  if (oldNode eq newNode) {
    newNode.domNode = oldNode.domNode
    return
  }

  (oldNode, newNode) match {
    case (oldTextNode: TextNode, newTextNode: TextNode) =>
      if (oldTextNode.text != newTextNode.text) {
        val textNode = oldTextNode.domNode.get.asInstanceOf[dom.Text]
        textNode.data = newTextNode.text
        newTextNode.domNode = Some(textNode)
      } else {
        newTextNode.domNode = oldTextNode.domNode
      }

    case (oldElemNode: ElementNode, newElemNode: ElementNode) =>
      if (oldElemNode.tag != newElemNode.tag) {
        // Replace the whole node
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
      val shouldReplace = oldComponentNode.componentFunction != newComponentNode.componentFunction ||
        oldComponentNode.props != newComponentNode.props

      if (shouldReplace) {
        // Re-render the component
        val newDomNode = renderDomNode(newComponentNode)
        val oldDomNode = oldComponentNode.domNode.getOrElse {
          throw new IllegalStateException("oldComponentNode.domNode is None")
        }
        parent.replaceChild(newDomNode, oldDomNode)
        newComponentNode.domNode = Some(newDomNode)
      } else {
        // Reuse the old component instance
        newComponentNode.instance = oldComponentNode.instance

        val instance = newComponentNode.instance.getOrElse {
          throw new IllegalStateException("Component instance is None")
        }
        instance.props = newComponentNode.props

        RenderContext.push(instance)
        instance.resetHooks()
        val childVNode = instance.renderFunction(instance.props)
        RenderContext.pop()

        val oldChildVNode = instance.renderedVNode.getOrElse {
          throw new IllegalStateException("ComponentInstance.renderedVNode is None")
        }

        // Use the same parent node for the child diffing
        diff(oldChildVNode, childVNode, parent)

        // Update the rendered VDOM reference
        instance.renderedVNode = Some(childVNode)

        // Update newComponentNode.domNode with the child domNode
        newComponentNode.domNode = childVNode.domNode

        // Execute the effects
        instance.effects.foreach(effect => effect())
        instance.effects.clear()
      }
    case _ =>
      // Nodes are of different types, replace old with new
      val oldDomNode = oldNode.domNode.getOrElse {
        throw new IllegalStateException("oldNode.domNode is None in default case")
      }
      val newDomNode = renderDomNode(newNode)
      parent.replaceChild(newDomNode, oldDomNode)
      newNode.domNode = Some(newDomNode)
  }
}

def renderDomNode(vnode: FluxusNode): dom.Node = vnode match {
  case TextNode(text) =>
    val textNode = dom.document.createTextNode(text)
    vnode.domNode = Some(textNode)
    textNode

  case ElementNode(tag, attributes, events, children) =>
    val element = dom.document.createElement(tag)

    attributes.foreach { case (name, value) =>
      element.setAttribute(name, value)
    }

    // Initialize eventListenerWrappers
    val elementNode = vnode.asInstanceOf[ElementNode]
    elementNode.eventListenerWrappers.clear()

    events.foreach { case (eventName, handler) =>
      val jsEventName                = eventName.stripPrefix("on").toLowerCase
      val wrapper: dom.Event => Unit = (_: dom.Event) => handler()
      element.addEventListener(jsEventName, wrapper)
      elementNode.eventListenerWrappers += (eventName -> wrapper)
    }

    children.foreach { child =>
      val childDomNode = renderDomNode(child)
      element.appendChild(childDomNode)
    }

    vnode.domNode = Some(element)
    element

  case componentNode @ ComponentNode(_, componentFunction, props, instanceOpt) =>
    // Create a new ComponentInstance and assign it to 'instance'
    val instance = instanceOpt.getOrElse {
      val newInstance = ComponentInstance(componentFunction, props)
      componentNode.instance = Some(newInstance)
      newInstance
    }

    // Push the instance onto the RenderContext stack
    RenderContext.push(instance)

    // Reset the hook index before rendering
    instance.resetHooks()

    // Render the component to get its virtual DOM
    val childVNode = componentFunction(props)

    // Pop the instance from the RenderContext stack
    RenderContext.pop()

    // Store the rendered VDOM
    instance.renderedVNode = Some(childVNode)

    // Render the child VNode into a DOM node
    val domNode = renderDomNode(childVNode)

    // Store the DOM node reference in both vnode and rendered VDOM
    vnode.domNode = Some(domNode)
    instance.renderedVNode.get.domNode = Some(domNode)

    // Execute the effects
    instance.effects.foreach(effect => effect())
    instance.effects.clear()

    // Return the DOM node
    domNode
}

def updateAttributes(domElement: dom.Element, oldAttrs: Map[String, String], newAttrs: Map[String, String]): Unit = {
  // Remove old attributes
  oldAttrs.keys.filterNot(newAttrs.contains).foreach { attr =>
    domElement.removeAttribute(attr)
  }

  // Update and add new attributes
  newAttrs.foreach { case (attr, value) =>
    if (oldAttrs.get(attr) != Some(value)) {
      domElement.setAttribute(attr, value)
    }
  }
}

def updateEvents(
    domElement: dom.Element,
    oldEvents: Map[String, () => Unit],
    newEvents: Map[String, () => Unit],
    oldVnode: ElementNode,
    newVnode: ElementNode,
): Unit = {
  // Remove event listeners that are no longer present or have changed
  oldEvents.foreach { case (eventName, oldHandler) =>
    if (!newEvents.contains(eventName) || oldHandler != newEvents(eventName)) {
      val jsEventName = eventName.stripPrefix("on").toLowerCase
      oldVnode.eventListenerWrappers.get(eventName) match {
        case Some(wrapper) =>
          domElement.removeEventListener(jsEventName, wrapper)
          newVnode.eventListenerWrappers -= eventName
        case None =>
          println(s"Warning: No wrapper found for event '$eventName' to remove.")
      }
    }
  }

  // Add new event listeners
  newEvents.foreach { case (eventName, handler) =>
    if (!oldEvents.contains(eventName) || oldEvents(eventName) != handler) {
      val jsEventName                = eventName.stripPrefix("on").toLowerCase
      val wrapper: dom.Event => Unit = (_: dom.Event) => handler()
      domElement.addEventListener(jsEventName, wrapper)
      // Update newVnode's eventListenerWrappers
      newVnode.eventListenerWrappers += (eventName -> wrapper)
    } else {
      // If the event listener hasn't changed, carry over the old wrapper
      oldVnode.eventListenerWrappers.get(eventName) match {
        case Some(wrapper) =>
          newVnode.eventListenerWrappers += (eventName -> wrapper)
        case None =>
          println(s"Warning: No wrapper found for event '$eventName' to carry over.")
      }
    }
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
        val newDomNode = renderDomNode(newChild)
        parent.appendChild(newDomNode)
        newChild.domNode = Some(newDomNode)

      case (Some(oldChild), None) =>
        oldChild.domNode match {
          case Some(domNode) => parent.removeChild(domNode)
          case None          => println("Warning: oldChild.domNode is None") // Handle the error or log a warning
        }

      case (None, None) => // Do nothing
    }
  }
}
