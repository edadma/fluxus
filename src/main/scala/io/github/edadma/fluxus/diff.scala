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
      // First, ensure instance reuse
      newComponentNode.instance = oldComponentNode.instance

      val instance = newComponentNode.instance.getOrElse {
        throw new IllegalStateException("Component instance is None")
      }

      // Always update props
      instance.props = newComponentNode.props

      // Always re-render since state might have changed
      RenderContext.push(instance)
      instance.resetHooks()
      val childVNode = instance.renderFunction(instance.props)
      RenderContext.pop()

      // Get the old child node for diffing
      val oldChildVNode = instance.renderedVNode.getOrElse {
        throw new IllegalStateException("ComponentInstance.renderedVNode is None")
      }

      // Diff children to update DOM
      diff(oldChildVNode, childVNode, parent)

      // Update references
      instance.renderedVNode = Some(childVNode)
      newComponentNode.domNode = childVNode.domNode

      // Execute effects
      instance.effects.foreach(effect => effect())
      instance.effects.clear()
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

    // Execute effects and clear them
    instance.effects.foreach(effect => effect())
    instance.effects.clear()

    domNode
}

def updateAttributes(domElement: dom.Element, oldAttrs: Map[String, String], newAttrs: Map[String, String]): Unit = {
  // Remove old attributes
  oldAttrs.keys.filterNot(newAttrs.contains).foreach { attr =>
    domElement.removeAttribute(attr)
  }

  // Update and add new attributes
  newAttrs.foreach { case (attr, value) =>
    if (!oldAttrs.get(attr).contains(value)) {
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
  println(s"Active event wrappers before cleanup: ${oldVnode.eventListenerWrappers.size}")

  // First, remove ALL old event listeners
  oldVnode.eventListenerWrappers.foreach { case (eventName, wrapper) =>
    val jsEventName = eventName.stripPrefix("on").toLowerCase
    domElement.removeEventListener(jsEventName, wrapper)
  }
  oldVnode.eventListenerWrappers.clear()

  // Then add all new event listeners
  newEvents.foreach { case (eventName, handler) =>
    val jsEventName                = eventName.stripPrefix("on").toLowerCase
    val wrapper: dom.Event => Unit = (_: dom.Event) => handler()
    domElement.addEventListener(jsEventName, wrapper)
    newVnode.eventListenerWrappers += (eventName -> wrapper)
  }

  println(s"New event wrappers after update: ${newVnode.eventListenerWrappers.size}")
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
