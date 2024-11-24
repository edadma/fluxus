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
private[fluxus] def renderApp(): Unit = {
  val startTime  = System.currentTimeMillis()
  val mountPoint = dom.document.getElementById(rootId)

  if (rootInstance == null) {
    rootInstance = ComponentInstance(rootComponent, makeProps())
  } else {
    rootInstance.resetHooks()
  }

  RenderContext.push(rootInstance)
  val beforeRender = System.currentTimeMillis()
  val newVNode     = rootInstance.renderFunction(rootInstance.props)
  val afterRender  = System.currentTimeMillis()
  RenderContext.pop()

  rootInstance.renderedVNode = Some(newVNode)

  if (oldVNode == null) {
    mountPoint.innerHTML = ""
    val domNode = renderDomNode(newVNode)
    mountPoint.appendChild(domNode)
  } else {
    val beforeDiff = System.currentTimeMillis()
    diff(oldVNode, newVNode, mountPoint)
    val afterDiff = System.currentTimeMillis()
    println(s"Diff took: ${afterDiff - beforeDiff}ms")
  }

  oldVNode = newVNode
  val endTime = System.currentTimeMillis()
  println(s"Total render took: ${endTime - startTime}ms")
}
