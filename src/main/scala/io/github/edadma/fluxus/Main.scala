package io.github.edadma.fluxus

import scala.language.postfixOps

@main def run(): Unit = renderApp("app", App)

// The root component of the app
def App(appProps: Props): FluxusNode = {
  // Use state within the component
  val (count, setCount) = useState(0)
  val (items, setItems) = useState(List(1, 2, 3))

  div(
    h1("Simple Scala.js App Framework"),
    p(s"Current count: $count"),
    button(
      "Increment",
      "onClick" -> (() => setCount(count + 1)),
    ),
    // Include a nested component with a unique ID
    component(CounterComponent)("initialCount" -> 5),
    div(
      button(
        "Add Item",
        "onClick" -> (() => setItems(items :+ (items.length + 1))),
      ),
      ul(
        items.map { item =>
          // Include the ListItemComponent, providing a 'key' prop
          component(ListItemComponent)("key" -> item.toString, "value" -> item)
        }*,
      ),
    ),
  )

}

def ListItemComponent(props: Props): FluxusNode = {
  val value = props.getOrElse("value", "").toString

  // Use useState if needed; for this example, we'll add a counter
  val (count, setCount) = useState(0)

  li(
    s"Item: $value, Count: $count",
    button(
      "Increment",
      "onClick" -> (() => setCount(count + 1)),
    ),
  )
}

// A nested component that takes props
def CounterComponent(componentProps: Props): FluxusNode = {
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
