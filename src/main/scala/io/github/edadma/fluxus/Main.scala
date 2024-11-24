package io.github.edadma.fluxus

import scala.language.postfixOps
import Implicits.*

import language.deprecated.symbolLiterals
import scala.scalajs.js

@main def run(): Unit = renderApp("app", App)

def App(appProps: Props): FluxusNode =
  val (count, setCount) = useState(0)
  val (items, setItems) = useState(List(1, 2, 3))

  div(
    cls := "container mx-auto p-4",
    h1(cls                         := "text-3xl font-bold mb-4", "Simple Scala.js App Framework"),
    p(cls                          := "stat-value text-secondary", s"Current count: $count"),
    button(cls                     := "btn btn-primary", "Increment", onClick := (() => setCount(count + 1))),
    CounterComponent('initialCount := 5),
    div(
      button(
        cls := "btn btn-secondary",
        "Add Item",
        onClick := (() => setItems(items :+ (items.length + 1))),
      ),
      ul(
        items.map { item =>
          // Include the ListItemComponent, providing a 'key' prop
          ListItemComponent('key := item.toString, 'value := item)
        }*,
      ),
    ),
    TimerComponent(),
  )

val ListItemComponent: FluxusComponent = (props: Props) =>
  val value             = props.getOrElse("value", "").toString
  val (count, setCount) = useState(0)

  li(
    s"Item: $value, Count: $count",
    button(cls := "btn btn-accent", "Increment", onClick := (() => setCount(count + 1))),
  )

// A nested component that takes props
val CounterComponent: FluxusComponent = (componentProps: Props) =>
  val initialCount      = componentProps("initialCount").asInstanceOf[Int]
  val (count, setCount) = useState(initialCount)

  div(
    h1("Nested Counter Component"),
    p(cls      := "stat-value text-primary", s"Nested count: $count"),
    button(cls := "btn btn-warning", "Increment Nested Counter", onClick := (() => setCount(count + 1))),
  )

val TimerComponent: FluxusComponent = (props: Props) =>
  val (seconds, setSeconds) = useState(0)

  useEffect(
    () => {
      println(s"Setting up interval: seconds = $seconds")
      val interval = js.timers.setInterval(1000) {
        println(s"Updating seconds: $seconds -> ${seconds + 1}")
        setSeconds(seconds + 1)
      }

      () => {
        println(s"Clearing interval: seconds = $seconds")
        js.timers.clearInterval(interval)
      }
    },
    Seq(seconds),
  )

  div(
    p(s"Timer: $seconds seconds"),
  )

//val TimerComponent: FluxusComponent = (props: Props) =>
//  val (seconds, setSeconds) = useState(0)
//
//  div(
//    p(s"Timer: $seconds seconds"),
//    button(
//      "Increment",
//      onClick := (() => setSeconds(seconds + 1)), // Manually increment to verify `useState`
//    ),
//  )
