package io.github.edadma.fluxus

import org.scalajs.dom

import scala.collection.mutable

// Global map to store component instances by ID
object GlobalState {
  val componentInstances: mutable.Map[String, ComponentInstance] = mutable.Map.empty
}

// The render function takes a VNode and a parent DOM element to mount to
def render(vnode: VNode, parent: dom.Element): Unit = vnode match {
  case TextNode(text) =>
    // Create a text node and append it to the parent
    val textNode = dom.document.createTextNode(text)
    parent.appendChild(textNode)

  case ElementNode(tag, attributes, events, children) =>
    // Create an element node with the specified tag
    val element = dom.document.createElement(tag)

    // Set attributes
    attributes.foreach { case (name, value) =>
      element.setAttribute(name, value)
    }

    // Set event listeners
    events.foreach { case (eventName, handler) =>
      val jsEventName = eventName.stripPrefix("on").toLowerCase
      element.addEventListener(jsEventName, (_: dom.Event) => handler())
    }

    // Save currentIndex before rendering children
    val parentIndex = RenderContext.currentIndex

    // Reset currentIndex to 0 for children
    RenderContext.currentIndex = 0

    // Render and append child nodes recursively
    for (child <- children) {
      render(child, element)
      RenderContext.currentIndex += 1 // Increment currentIndex after each child
    }

    // Restore currentIndex after rendering children
    RenderContext.currentIndex = parentIndex

    // Append the element to the parent
    parent.appendChild(element)

  case ComponentNode(componentFunction, props) =>
    import GlobalState._

    // Generate the ID using the RenderContext
    val id = RenderContext.currentId

    // Retrieve or create the component instance
    val instance = componentInstances.getOrElseUpdate(id, ComponentInstance(componentFunction, props))

    // Update props in case they have changed
    instance.props = props

    // Push the instance and update RenderContext
    RenderContext.push(instance)

    // Reset hooks before rendering
    instance.resetHooks()

    // Render the component to get the VNode
    val childVNode = instance.renderFunction(instance.props)

    // Render the child VNode into the parent
    render(childVNode, parent)

    // Pop the instance and update indices
    RenderContext.pop()
}
