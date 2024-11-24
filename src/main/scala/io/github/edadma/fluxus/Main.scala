package io.github.edadma.fluxus

import scala.language.postfixOps
import Implicits.*

import language.deprecated.symbolLiterals
import scala.scalajs.js
import scala.scalajs.js.timers

@main def run(): Unit = renderApp("app", App)

object RenderTracker {
  private var renderCount = 0
  private var updateCount = 0

  def trackRender(componentName: String): FluxusNode => FluxusNode = { node =>
    renderCount += 1
    println(s"[Render #$renderCount] $componentName")
    node
  }

  def logStateUpdate(): Unit = {
    updateCount += 1
    println(s"[State Update #$updateCount]")
  }
}

def App(appProps: Props): FluxusNode =
  RenderTracker.trackRender("App"):
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
          onClick := (() => setItems(items :+ (items.length + 1))), // prev => prev :+ (prev.length + 1))
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
  RenderTracker.trackRender("ListItem"):
    val value             = props.getOrElse("value", "").toString
    val (count, setCount) = useState(0)

    li(
      s"Item: $value, Count: $count",
      button(cls := "btn btn-accent", "Increment", onClick := (() => setCount(count + 1))),
    )

// A nested component that takes props
val CounterComponent: FluxusComponent = (componentProps: Props) =>
  RenderTracker.trackRender("Counter"):
    val initialCount      = componentProps("initialCount").asInstanceOf[Int]
    val (count, setCount) = useState(initialCount)

    div(
      h1("Nested Counter Component"),
      p(cls      := "stat-value text-primary", s"Nested count: $count"),
      button(cls := "btn btn-warning", "Increment Nested Counter", onClick := (() => setCount(count + 1))),
    )

val TimerComponent: FluxusComponent = (_: Props) =>
  RenderTracker.trackRender("Timer"):
    val (seconds, setSeconds) = useState(0)

    useEffect(
      () => {
        val intervalId = timers.setInterval(1000) {
          setSeconds((_: Int) + 1)
        }

        () => timers.clearInterval(intervalId)
      },
      Seq(),
    )

    div(
      h1("Timer Component"),
      p(s"Seconds elapsed: $seconds"),
    )
