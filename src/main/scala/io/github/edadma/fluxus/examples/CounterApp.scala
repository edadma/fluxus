package io.github.edadma.fluxus.examples

import io.github.edadma.fluxus.api.Element._
import io.github.edadma.fluxus.core.types._
import io.github.edadma.fluxus.core.hooks.Hooks._
import io.github.edadma.fluxus.core.dom.DOMOperations
import io.github.edadma.fluxus.logging.Logger
import io.github.edadma.fluxus.logging.Logger.{Category, LogLevel}
import org.scalajs.dom

case class CounterProps()

object CounterApp {
  def Counter(props: CounterProps): FluxusNode = {
    val opId = Logger.nextOperationId
    Logger.debug(Category.Component, "Rendering Counter component", opId)

    val (count, setCount) = useState(0)

    def handleIncrement(e: dom.Event): Unit = {
      val clickOpId = Logger.nextOperationId
      Logger.debug(
        Category.Component,
        "Increment button clicked",
        clickOpId,
        Map("currentCount" -> count),
      )
      setCount(count + 1)
    }

    def handleDecrement(e: dom.Event): Unit = {
      val clickOpId = Logger.nextOperationId
      Logger.debug(
        Category.Component,
        "Decrement button clicked",
        clickOpId,
        Map("currentCount" -> count),
      )
      setCount(count - 1)
    }

    div(
      cls := "flex flex-col items-center gap-4 p-8",
      h1(
        cls := "text-2xl font-bold",
        "Counter Example",
      ),
      div(
        cls := "text-4xl font-bold",
        count.toString,
      ),
      div(
        cls := "flex gap-4",
        button(
          cls     := "px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600",
          onClick := handleIncrement,
          "+",
        ),
        button(
          cls     := "px-4 py-2 bg-red-500 text-white rounded hover:bg-red-600",
          onClick := handleDecrement,
          "-",
        ),
      ),
    )
  }

  @main def run(): Unit = {
    Logger.setLevel(LogLevel.DEBUG)
    val opId = Logger.nextOperationId

    Logger.info(Category.Component, "Starting counter app", opId)

    // Get container
    val container = dom.document.getElementById("app")

    // Create counter component
    val counter = Component.create(
      render = Counter.asInstanceOf[Any => FluxusNode],
      props = CounterProps(),
      key = None,
      opId = opId,
      name = Some("Counter"), // Explicitly name it Counter
    )

    Logger.debug(
      Category.Component,
      "Created counter component",
      opId,
      Map(
        "hasInstance" -> counter.instance.isDefined,
        "hasRendered" -> counter.instance.flatMap(_.rendered).isDefined,
      ),
    )

    // Mount the app
    DOMOperations.mount(counter, container.asInstanceOf[dom.Element])
  }
}
