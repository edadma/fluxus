package io.github.edadma.fluxus

import org.scalajs.dom

def diff(oldNode: FluxusNode, newNode: FluxusNode, parent: dom.Node): Unit = {
  // If the nodes are the same, do nothing
  if (oldNode == newNode) return

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
        updateEvents(domElement, oldElemNode.events, newElemNode.events)
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
        newComponentNode.domNode = oldComponentNode.domNode

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

        // Diff the child VDOM
        diff(oldChildVNode, childVNode, parent)

        // Update the rendered VDOM reference
        instance.renderedVNode = Some(childVNode)
      }

    case _ =>
      // Nodes are of different types, replace old with new
      val newDomNode = renderDomNode(newNode)
      parent.replaceChild(newDomNode, oldNode.domNode.get)
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

    events.foreach { case (eventName, handler) =>
      val jsEventName = eventName.stripPrefix("on").toLowerCase
      element.addEventListener(jsEventName, (_: dom.Event) => handler())
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

    // Store the DOM node reference in the vnode
    vnode.domNode = Some(domNode)

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
): Unit = {
  // For simplicity, re-add events
  oldEvents.foreach { case (eventName, handler) =>
    val jsEventName = eventName.stripPrefix("on").toLowerCase
    domElement.removeEventListener(jsEventName, (_: dom.Event) => handler())
  }

  newEvents.foreach { case (eventName, handler) =>
    val jsEventName = eventName.stripPrefix("on").toLowerCase
    domElement.addEventListener(jsEventName, (_: dom.Event) => handler())
  }
}

def diffChildren(oldChildren: List[FluxusNode], newChildren: List[FluxusNode], parent: dom.Element): Unit = {
  val maxLength = Math.max(oldChildren.length, newChildren.length)
  for (i <- 0 until maxLength) {
    (oldChildren.lift(i), newChildren.lift(i)) match {
      case (Some(oldChild), Some(newChild)) =>
        diff(oldChild, newChild, parent)

      case (None, Some(newChild)) =>
        // New child added
        val newDomNode = renderDomNode(newChild)
        parent.appendChild(newDomNode)

      case (Some(oldChild), None) =>
        // Old child removed
        parent.removeChild(oldChild.domNode.get)

      case (None, None) => // Do nothing
    }
  }
}
