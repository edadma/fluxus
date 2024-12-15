//package io.github.edadma.fluxus.testing
//
//import io.github.edadma.fluxus.*
//import io.github.edadma.fluxus.core.*
//
//import scala.concurrent.Future
//import scala.scalajs.js
//
//class BatchSchedulerTest extends AsyncDOMSpec {
//  // Import JavaScript execution context for Futures
////  import scala.concurrent.ExecutionContext.Implicits.global
//
//  "BatchScheduler" should "batch multiple state updates" in withDebugLogging("batch multiple state updates") {
//    val container   = getContainer
//    var renderCount = 0
//
//    case class MultiStateProps()
//
//    def MultiStateComponent(props: MultiStateProps): FluxusNode = {
//      logger.debug(
//        "Rendering MultiStateComponent",
//        category = "Test",
//        Map("renderCount" -> renderCount.toString),
//      )
//      renderCount += 1
//      val (count1, setCount1) = useState(0)
//      val (count2, setCount2) = useState(0)
//
//      div(
//        p(cls := "count1", s"Count1: $count1"),
//        p(cls := "count2", s"Count2: $count2"),
//        button(
//          onClick := (() => {
//            logger.debug(
//              "Button clicked - before updates",
//              category = "Test",
//              Map(
//                "count1" -> count1.toString,
//                "count2" -> count2.toString,
//              ),
//            )
//            setCount1(count1 + 1)
//            setCount2(count2 + 1)
//            logger.debug(
//              "Button clicked - after updates",
//              category = "Test",
//              Map(
//                "count1" -> count1.toString,
//                "count2" -> count2.toString,
//              ),
//            )
//          }),
//          "Increment Both",
//        ),
//      )
//    }
//
//    // Initial render
//    val node = MultiStateComponent <> MultiStateProps()
//    createDOM(node, container)
//
//    // Initial assertions
//    renderCount shouldBe 1
//
//    // Click button
//    click(container.querySelector("button"))
//
//    // Create a Future that will complete after the next event loop
//    new js.Promise[org.scalatest.compatible.Assertion]((resolve, reject) => {
//      js.timers.setTimeout(0) {
//        try {
//          val assertion = {
//            renderCount shouldBe 2
//            container.querySelector(".count1").textContent shouldBe "Count1: 1"
//            container.querySelector(".count2").textContent shouldBe "Count2: 1"
//            succeed
//          }
//          resolve(assertion)
//        } catch {
//          case e: Throwable =>
//            logger.error(
//              "Test assertions failed",
//              category = "Test",
//              Map(
//                "error"       -> e.getMessage,
//                "renderCount" -> renderCount.toString,
//                "count1"      -> Option(container.querySelector(".count1")).map(_.textContent).getOrElse("null"),
//                "count2"      -> Option(container.querySelector(".count2")).map(_.textContent).getOrElse("null"),
//              ),
//            )
//            reject(e)
//        }
//      }
//    }).toFuture
//  }
//}
