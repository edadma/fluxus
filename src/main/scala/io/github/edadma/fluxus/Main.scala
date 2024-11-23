package io.github.edadma.fluxus

import org.scalajs.dom

@main def run(): Unit =
  // Call renderApp to render the app for the first time
  renderApp()

// Declare a variable to hold the rootInstance
var rootInstance: ComponentInstance = null

// The root component of the app
def App(appProps: Props): VNode = {
  // Use state within the component
  val (count, setCount) = useState(0)

  div(
    h1("Simple Scala.js App Framework"),
    p(s"Current count: $count"),
    button(
      "Increment",
      "onClick" -> (() => setCount(count + 1)),
    ),
    // Include a nested component with a unique ID
    component(CounterComponent)("initialCount" -> 5),
  )
}

// A nested component that takes props
def CounterComponent(componentProps: Props): VNode = {
  val initialCount      = componentProps("initialCount").asInstanceOf[Int]
  val (count, setCount) = useState(initialCount)

  div(
    h1("Nested Counter Component"),
    p(s"Nested count: $count"),
    button(
      "Increment Nested Counter",
      "onClick" -> (() => setCount(count + 1)),
    ),
  )
}

// The function to render the app
def renderApp(): Unit = {
  // Get the mount point from the DOM
  val mountPoint = dom.document.getElementById("app")

  // Clear previous content
  mountPoint.innerHTML = ""

  // If rootInstance is null (first render), create it
  if (rootInstance == null) {
    rootInstance = ComponentInstance(App, makeProps())
  } else {
    // Before re-rendering, reset hooks
    rootInstance.resetHooks()
  }

  // Push the root instance onto the render context stack
  RenderContext.push(rootInstance)

  // Render the root component to a VNode
  val appVNode = rootInstance.renderFunction(rootInstance.props)

  // Render the VNode to the real DOM
  render(appVNode, mountPoint)

  // Pop the root instance from the render context stack
  RenderContext.pop()
}
