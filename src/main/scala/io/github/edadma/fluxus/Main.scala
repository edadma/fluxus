package io.github.edadma.fluxus

import scala.language.postfixOps

import Implicits.*

@main def run(): Unit = renderApp("app", App)

def App(appProps: Props): FluxusNode =
  val (count, setCount) = useState(0)
  val (items, setItems) = useState(List(1, 2, 3))

  div(
    "class" -> "container mx-auto p-4",
    h1("class" -> "text-3xl font-bold mb-4", "Simple Scala.js App Framework"),
    p(s"Current count: $count"),
    button("class"                  -> "btn btn-primary", "Increment", "onClick" -> (() => setCount(count + 1))),
    CounterComponent("initialCount" -> 5),
    div(
      button(
        "class" -> "btn btn-secondary",
        "Add Item",
        "onClick" -> (() => setItems(items :+ (items.length + 1))),
      ),
      ul(
        items.map { item =>
          // Include the ListItemComponent, providing a 'key' prop
          ListItemComponent("key" -> item.toString, "value" -> item)
        }*,
      ),
    ),
  )

val ListItemComponent: FluxusComponent = (props: Props) =>
  val value             = props.getOrElse("value", "").toString
  val (count, setCount) = useState(0)

  li(
    s"Item: $value, Count: $count",
    button("class" -> "btn btn-accent", "Increment", "onClick" -> (() => setCount(count + 1))),
  )

// A nested component that takes props
val CounterComponent: FluxusComponent = (componentProps: Props) =>
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
