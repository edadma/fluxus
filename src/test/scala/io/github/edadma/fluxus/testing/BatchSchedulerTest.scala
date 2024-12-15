//package io.github.edadma.fluxus.testing
//
//import io.github.edadma.fluxus.*
//import io.github.edadma.fluxus.core.*
//
//class BatchSchedulerTest extends AsyncDOMSpec {
//  "BatchScheduler" should "batch multiple state updates" in {
//    val container                               = getContainer
//    var renderCount                             = 0
//    var promise: scala.scalajs.js.Promise[Unit] = null
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
//
//            // Capture the promise for test synchronization
//            promise = scala.scalajs.js.Promise.resolve(())
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
//    // Verify initial render
//    renderCount shouldBe 1
//
//    // Trigger updates
//    click(container.querySelector("button"))
//
//    // Wait for promise to resolve
//    promise.`then`(_ => {
//      renderCount shouldBe 2
//      container.querySelector(".count1").textContent shouldBe "Count1: 1"
//      container.querySelector(".count2").textContent shouldBe "Count2: 1"
//    })
//  }
//}
