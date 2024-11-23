package io.github.edadma.fluxus

import org.scalajs.dom

def render(component: => VNode, mountNode: String): Unit = {
  val mountPoint = dom.document.querySelector(mountNode)

  if (mountPoint == null) {
    throw new IllegalArgumentException(s"Mount node $mountNode not found.")
  }

  // Create a new component instance and set the render context
  val instance = ComponentInstance(() => component)

  RenderContext.set(instance)

  val vnode = instance.render()

  RenderContext.clear()

  // Clear the DOM and mount the new VNode
  mountPoint.innerHTML = "" // Clear the mount point
  mountVNode(vnode, mountPoint)
}

private def mountVNode(vnode: VNode, parent: dom.Element): Unit = vnode match {
  case TextNode(text) =>
    parent.appendChild(dom.document.createTextNode(text))

  case ElementNode(tag, attributes, events, children) =>
    val element = dom.document.createElement(tag)

    // Set attributes
    attributes.foreach { case (key, value) =>
      element.setAttribute(key, value)
    }

    // Add event listeners
    events.foreach { case (event, handler) =>
      element.addEventListener(event, _ => handler())
    }

    // Render children
    children.foreach(child => mountVNode(child, element))
    parent.appendChild(element)
}
