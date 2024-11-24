/* renderApp.scala

This file defines the entry point for rendering the Fluxus application.
It manages the root component and handles the initial render and subsequent re-renders of the app.

Key components:
- Variables to store the root component instance, its ID, and the component function.
- `renderApp` function: Overloaded to handle initial setup and subsequent re-renders.
- Integration with the rendering system and the `RenderContext`.

 */

package io.github.edadma.fluxus // Define the package namespace for the Fluxus framework

import org.scalajs.dom // Import the DOM library for interacting with the browser's DOM

import scala.compiletime.uninitialized // Import `uninitialized` to declare uninitialized variables

// Variables to store the root component instance, its ID, and the component function
var rootInstance: ComponentInstance = uninitialized // The root component instance of the app
var rootId: String                  = uninitialized // The ID of the DOM element where the app will be mounted
var rootComponent: FluxusComponent  = uninitialized // The root component function of the app

var oldVNode: FluxusNode = null

// The `renderApp` function initializes the app by setting the root ID and component, and then triggers the rendering
def renderApp(id: String, component: FluxusComponent): Unit = {
  rootId = id               // Store the ID of the mount point
  rootComponent = component // Store the root component function
  renderApp()               // Call the internal renderApp to perform the rendering
}

// The internal `renderApp` function performs the actual rendering of the app
private[fluxus] def renderApp(): Unit =
  val mountPoint = dom.document.getElementById(rootId) // Get the DOM element by ID where the app will be mounted
  mountPoint.innerHTML = "" // Clear the mount point's content before rendering

  if (rootInstance == null) {
    rootInstance =
      ComponentInstance(rootComponent, makeProps()) // Create a new ComponentInstance for the root component
  } else {
    rootInstance.resetHooks() // Reset hooks if the root instance already exists
  }

  // Reset the component ID counter at the beginning of each render
  RenderContext.componentIdCounter = 0 // Ensures consistent IDs across renders

  RenderContext.push(rootInstance) // Push the root instance onto the RenderContext stack

//  val appVNode =
//    rootInstance.renderFunction(rootInstance.props) // Call the root component's render function to get the virtual DOM
//  render(appVNode, mountPoint)                      // Render the virtual DOM into the mount point
//  RenderContext.pop()                               // Pop the root instance from the RenderContext stack

  val newVNode = rootInstance.renderFunction(rootInstance.props)
  RenderContext.pop()

  // Store the rendered VDOM in the root instance
  rootInstance.renderedVNode = Some(newVNode)

  // Perform the diff and update the DOM
  if (oldVNode == null) {
    // First render
    mountPoint.innerHTML = "" // Clear the mount point's content before rendering
    val domNode = renderDomNode(newVNode)
    mountPoint.appendChild(domNode)
  } else {
    // Diff the old and new VDOM and update the DOM
    diff(oldVNode, newVNode, mountPoint)
  }

  // Update the old VDOM reference
  oldVNode = newVNode
