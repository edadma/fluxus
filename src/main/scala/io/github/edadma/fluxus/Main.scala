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
  val mountPoint = dom.document.getElementById("app")
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
