package io.github.edadma.fluxus

import org.scalajs.dom

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

    // Render and append child nodes recursively
    children.foreach(child => render(child, element))

    // Append the element to the parent
    parent.appendChild(element)
}
