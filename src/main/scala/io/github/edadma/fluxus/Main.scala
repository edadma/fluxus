package io.github.edadma.fluxus

import org.scalajs.dom

@main def run(): Unit =
  // Call renderApp to render the app for the first time
  renderApp()

// The root component of the app
def App(props: Props): VNode = {
  // Use state within the component
  val (count, setCount) = useState(0)

  div(
    h1("Simple Scala.js App Framework"),
    p(s"Current count: $count"),
    button(
      "Increment",
      // Event handler to update the state
      "onClick" -> (() => setCount(count + 1)),
    ),
    // Include a nested component and pass props to it
    CounterComponent(props("initialCount").asInstanceOf[Int]),
  )
}

// A nested component that takes props
def CounterComponent(initialCount: Int): VNode = {
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

  // Create a new component instance for the root component
  val rootInstance = ComponentInstance(App, props("initialCount" -> 5))

  // Push the root instance onto the render context stack
  RenderContext.push(rootInstance)

  // Render the root component to a VNode
  val appVNode = rootInstance.renderFunction(rootInstance.props)

  // Render the VNode to the real DOM
  render(appVNode, mountPoint)

  // Pop the root instance from the render context stack
  RenderContext.pop()
}
