/* renderApp.scala

This file defines the entry point for rendering the Fluxus application.
It manages the root component and handles the initial render and subsequent re-renders of the app.

Key components:
- Variables to store the root component instance, its ID, and the component function.
- `renderApp` function: Overloaded to handle initial setup and subsequent re-renders.
- Integration with the rendering system and the `RenderContext`.

 */

package io.github.edadma.fluxus // Define the package namespace for the Fluxus framework

import org.scalajs.dom

import scala.compiletime.uninitialized
import scala.scalajs.js // Import `uninitialized` to declare uninitialized variables

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

object PerformanceMonitor {
  private var lastRender  = js.Date.now()
  private var renderCount = 0

  def logRender(): Unit = {
    renderCount += 1
    val now                 = js.Date.now()
    val timeSinceLastRender = now - lastRender
    println(s"Render #$renderCount, Time since last render: ${timeSinceLastRender}ms")
    lastRender = now

    // Log heap size if available
    val mem = js.Dynamic.global.console.memory

    println(s"Used JS heap size: ${mem.usedJSHeapSize.asInstanceOf[Double] / (1024 * 1024)}MB")
  }
}

// The internal `renderApp` function performs the actual rendering of the app
private[fluxus] def renderApp(): Unit = {
  PerformanceMonitor.logRender()

  val mountPoint = dom.document.getElementById(rootId)

  if (rootInstance == null) {
    rootInstance = ComponentInstance(rootComponent, makeProps())
  } else {
    rootInstance.resetHooks()
  }

  RenderContext.push(rootInstance)
  val newVNode = rootInstance.renderFunction(rootInstance.props)
  RenderContext.pop()

  rootInstance.renderedVNode = Some(newVNode)

  if (oldVNode == null) {
    mountPoint.innerHTML = ""
    val domNode = renderDomNode(newVNode)
    mountPoint.appendChild(domNode)
  } else {
    diff(oldVNode, newVNode, mountPoint)
  }

  oldVNode = newVNode
}
