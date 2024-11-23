package io.github.edadma.fluxus

import org.scalajs.dom

def render(component: () => VNode, mountNode: String): Unit = {
  val mountPoint = dom.document.querySelector(mountNode)
  if (mountPoint == null) {
    println(s"ERROR: Mount node '$mountNode' not found.")
    throw new IllegalStateException(s"Mount point '$mountNode' not found in DOM.")
  }

  println(s"INFO: Rendering to mount point '$mountNode'.")

  // Use the current instance if available, or create a new one
  val instance = RenderContext.currentInstance.getOrElse {
    val newInstance = ComponentInstance(component)
    RenderContext.set(newInstance)
    newInstance
  }

  // Reset hooks and proceed with rendering
  println("DEBUG: Resetting hooks before rendering...")
  instance.resetHooks()

  // Generate the virtual DOM tree
  println("DEBUG: Rendering the component tree...")
  val vnode = instance.render()
  println(s"DEBUG: Rendered VNode: $vnode")

  // Clear the current DOM content before mounting the new tree
  println("DEBUG: Clearing the DOM mount point...")
  mountPoint.innerHTML = ""

  // Mount the virtual DOM to the real DOM
  println("DEBUG: Mounting the new virtual DOM...")
  mountVNode(vnode, mountPoint)

  // Do not clear the RenderContext here, so the instance is preserved
  // If you need to clear other context, make sure to preserve currentInstance
}

private def mountVNode(vnode: VNode, parent: dom.Element): Unit = vnode match {
  case TextNode(text) =>
    parent.appendChild(dom.document.createTextNode(text))

  case ElementNode(tag, attributes, events, children) =>
    val element = dom.document.createElement(tag)

    // Set attributes
    for ((key, value) <- attributes) {
      element.setAttribute(key, value)
    }

    // Set event handlers
    events.foreach { case (event, handler) =>
      val jsEventName = event.stripPrefix("on").toLowerCase
      println(s"DEBUG: Attaching event listener for '$jsEventName'")
      element.addEventListener(
        jsEventName,
        (_: dom.Event) => {
          println(s"DEBUG: Event '$jsEventName' triggered")
          handler()
        },
      )
    }

    // Mount children
    children.foreach {
      case childVNode: VNode => mountVNode(childVNode, element)
      case other =>
        throw new IllegalStateException(s"Unsupported child node type: $other in ElementNode($tag)")
    }

    parent.appendChild(element)

  case other =>
    throw new IllegalStateException(s"Unsupported VNode type: $other")
}
