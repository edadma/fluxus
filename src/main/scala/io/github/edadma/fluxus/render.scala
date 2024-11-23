// render.scala

package io.github.edadma.fluxus

import org.scalajs.dom

import scala.collection.mutable

// Global map to store component instances by ID
object GlobalState {
  val componentInstances: mutable.Map[String, ComponentInstance] = mutable.Map.empty
}

def render(vnode: FluxusNode, parent: dom.Element): Unit = vnode match {
  case TextNode(text) =>
    val textNode = dom.document.createTextNode(text)
    parent.appendChild(textNode)

  case ElementNode(tag, attributes, events, children) =>
    val element = dom.document.createElement(tag)

    attributes.foreach { case (name, value) =>
      element.setAttribute(name, value)
    }

    events.foreach { case (eventName, handler) =>
      val jsEventName = eventName.stripPrefix("on").toLowerCase
      element.addEventListener(jsEventName, (_: dom.Event) => handler())
    }

    // Render and append child nodes recursively
    children.foreach(child => render(child, element))

    parent.appendChild(element)

  case ComponentNode(keyOption, componentFunction, props) =>
    import GlobalState._

    val id = keyOption.getOrElse {
      // Use the component ID counter if no key is provided
      val id = RenderContext.componentIdCounter.toString
      RenderContext.componentIdCounter += 1
      id
    }
    RenderContext.componentIdCounter += 1

    // Retrieve or create the component instance
    val instance = componentInstances.getOrElseUpdate(id, ComponentInstance(componentFunction, props))

    // Update props in case they have changed
    instance.props = props

    // Push the instance onto the render context stack
    RenderContext.push(instance)

    // Reset hooks before rendering
    instance.resetHooks()

    // Render the component to get the VNode
    val childVNode = instance.renderFunction(instance.props)

    // Render the child VNode into the parent
    render(childVNode, parent)

    // Pop the instance from the render context stack
    RenderContext.pop()
}
