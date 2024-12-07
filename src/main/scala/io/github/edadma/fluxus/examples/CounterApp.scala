//package io.github.edadma.fluxus.examples
//
//import io.github.edadma.fluxus.api.Element._
//import io.github.edadma.fluxus.core.types._
//import io.github.edadma.fluxus.core.hooks.Hooks._
//import io.github.edadma.fluxus.core.dom.DOMOperations
//import io.github.edadma.fluxus.logging.Logger
//import io.github.edadma.fluxus.logging.Logger.{Category, LogLevel}
//import org.scalajs.dom
//
//object CounterApp {
//  case class CounterProps()
//
//  def Counter(props: CounterProps): FluxusNode = {
//    val opId = Logger.nextOperationId
//    Logger.debug(Category.Component, "Rendering Counter component", opId)
//
//    val (count, setCount) = useState(0)
//
//    def handleIncrement(e: dom.Event): Unit = {
//      val clickOpId = Logger.nextOperationId
//      Logger.debug(
//        Category.Component,
//        "Increment button clicked",
//        clickOpId,
//        Map("currentCount" -> count),
//      )
//      setCount(_ + 1)
//    }
//
//    def handleDecrement(e: dom.Event): Unit = {
//      val clickOpId = Logger.nextOperationId
//      Logger.debug(
//        Category.Component,
//        "Decrement button clicked",
//        clickOpId,
//        Map("currentCount" -> count),
//      )
//      setCount(_ - 1)
//    }
//
//    div(
//      cls := "flex flex-col items-center justify-center min-h-screen bg-base-200",
//      div(
//        cls := "card w-96 bg-base-100 shadow-xl",
//        div(
//          cls := "card-body items-center text-center",
//          h2(
//            cls := "card-title text-3xl mb-4",
//            "Counter Example",
//          ),
//          div(
//            cls := "stats shadow mb-4",
//            div(
//              cls := "stat",
//              div(
//                cls := "stat-title",
//                "Current Count",
//              ),
//              div(
//                cls := "stat-value text-4xl",
//                count.toString,
//              ),
//              div(
//                cls   := "stat-desc animate-[bounce_0.3s_ease-in-out] text-lg",
//                style := "transform-origin: center;",
//                if (count > 0) "Positive"
//                else if (count < 0) "Negative"
//                else "Zero",
//              ),
//            ),
//          ),
//          div(
//            cls := "card-actions",
//            button(
//              cls     := "btn btn-primary btn-lg",
//              onClick := handleDecrement,
//              "-",
//            ),
//            button(
//              cls     := "btn btn-primary btn-lg",
//              onClick := handleIncrement,
//              "+",
//            ),
//          ),
//        ),
//      ),
//    )
//  }
//
//  @main def run(): Unit = {
//    Logger.setLevel(LogLevel.DEBUG)
//    val opId = Logger.nextOperationId
//
//    Logger.info(Category.Component, "Starting counter app", opId)
//
//    // Get container
//    val container = dom.document.getElementById("app")
//
//    // Create counter component
//    val counter = Component.create(
//      render = Counter.asInstanceOf[Any => FluxusNode],
//      props = CounterProps(),
//      key = None,
//      opId = opId,
//      name = Some("Counter"),
//    )
//
//    Logger.debug(
//      Category.Component,
//      "Created counter component",
//      opId,
//      Map(
//        "hasInstance" -> counter.instance.isDefined,
//        "hasRendered" -> counter.instance.flatMap(_.rendered).isDefined,
//      ),
//    )
//
//    // Mount the app
//    DOMOperations.mount(counter, container)
//  }
//}
