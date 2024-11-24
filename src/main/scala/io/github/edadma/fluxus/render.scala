/* render.scala

This file defines the rendering logic for the Fluxus framework.
The `render` function takes a virtual DOM node (`FluxusNode`) and a parent DOM element, and renders the virtual node into real DOM elements.

Key components:
- GlobalState: Stores component instances by ID to preserve state across renders.
- `render` function: Recursively renders virtual nodes into DOM elements.
- Handling of TextNode, ElementNode, and ComponentNode cases.

 */

package io.github.edadma.fluxus // Define the package namespace for the Fluxus framework

import org.scalajs.dom // Import the DOM library for interacting with the browser's DOM

import scala.collection.mutable // Import mutable collections for storing state

// Global map to store component instances by ID
object GlobalState {
  val componentInstances: mutable.Map[String, ComponentInstance] = mutable.Map.empty
  /*
    - Stores instances of components keyed by their unique IDs.
    - Allows retrieval and preservation of component state across renders.
    - Essential for managing stateful components and hooks like useState.
   */
}

// The render function takes a FluxusNode (virtual DOM node) and a parent DOM element to mount to
def render(vnode: FluxusNode, parent: dom.Element): Unit = vnode match {
  case TextNode(text) =>
    // Handle text nodes by creating a DOM text node
    val textNode = dom.document.createTextNode(text) // Create a text node with the given text
    parent.appendChild(textNode) // Append the text node to the parent element

  case ElementNode(tag, attributes, events, children) =>
    // Handle element nodes by creating a DOM element and rendering its children
    val element = dom.document.createElement(tag) // Create a DOM element with the specified tag name

    attributes.foreach { case (name, value) =>
      element.setAttribute(name, value) // Set each attribute on the element
    }

    events.foreach { case (eventName, handler) =>
      val jsEventName =
        eventName.stripPrefix("on").toLowerCase // Convert event name to JavaScript event (e.g., "onClick" -> "click")
      element.addEventListener(jsEventName, (_: dom.Event) => handler()) // Add event listener to the element
    }

    // Render and append child nodes recursively
    children.foreach(child => render(child, element)) // Recursively render each child into the element

    parent.appendChild(element) // Append the element to the parent DOM node

  case ComponentNode(keyOption, componentFunction, props) =>
    import GlobalState._ // Import the GlobalState object to access componentInstances

    // Determine the unique ID for the component instance
    val id = keyOption.getOrElse {
      // Use the component ID counter if no key is provided
      val id = RenderContext.componentIdCounter.toString // Convert the counter to a string to use as an ID
      RenderContext.componentIdCounter += 1 // Increment the counter for the next component
      id // Return the generated ID
    }
    RenderContext.componentIdCounter += 1 // Increment the counter even if a key is provided

    // Retrieve or create the component instance
    val instance = componentInstances.getOrElseUpdate(id, ComponentInstance(componentFunction, props))
    /*
      - Retrieves the ComponentInstance associated with the ID from componentInstances.
      - If it doesn't exist, creates a new ComponentInstance and stores it in the map.
      - Ensures that the component's state is preserved across renders.
     */

    // Update props in case they have changed
    instance.props = props // Update the component's props with the latest values

    // Push the instance onto the render context stack
    RenderContext.push(instance) // Update the rendering context to include this component

    // Reset hooks before rendering
    instance.resetHooks() // Reset the hook index to 0 before rendering

    // Render the component to get its virtual DOM
    val childVNode = instance.renderFunction(instance.props) // Call the component's render function with its props

    // Render the child VNode into the parent
    render(childVNode, parent) // Recursively render the component's virtual DOM into the parent

    // Now execute the effects
    instance.effects.foreach(effect => effect())
    instance.effects.clear()

    // Pop the instance from the render context stack
    RenderContext.pop() // Restore the previous rendering context
}
