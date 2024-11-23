package io.github.edadma.fluxus

import org.scalajs.dom

import scala.compiletime.uninitialized

var rootInstance: ComponentInstance = uninitialized
var rootId: String                  = uninitialized

def renderApp(root: String): Unit = {
  rootId = root
  renderApp()
}

private[fluxus] def renderApp(): Unit = {
  val mountPoint = dom.document.getElementById(rootId)
  mountPoint.innerHTML = ""

  if (rootInstance == null) {
    rootInstance = ComponentInstance(App, makeProps())
  } else {
    rootInstance.resetHooks()
  }

  // Reset the component ID counter at the beginning of each render
  RenderContext.componentIdCounter = 0

  RenderContext.push(rootInstance)
  val appVNode = rootInstance.renderFunction(rootInstance.props)
  render(appVNode, mountPoint)
  RenderContext.pop()
}
