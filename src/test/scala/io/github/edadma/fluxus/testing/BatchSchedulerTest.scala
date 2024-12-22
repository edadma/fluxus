package io.github.edadma.fluxus.testing

import io.github.edadma.fluxus.*
import io.github.edadma.fluxus.core.*

import scala.scalajs.js

class BatchSchedulerTest extends AsyncDOMSpec {

  "BatchScheduler" should "batch multiple state updates" in /*withDebugLogging("batch multiple state updates")*/ {
    val container   = getContainer
    var renderCount = 0

    case class MultiStateProps()

    def MultiStateComponent(props: MultiStateProps): FluxusNode = {
      logger.debug(
        "Rendering MultiStateComponent",
        category = "Test",
        Map("renderCount" -> renderCount.toString),
      )
      renderCount += 1
      val (count1, setCount1, _) = useState(0)
      val (count2, setCount2, _) = useState(0)

      div(
        p(cls := "count1", s"Count1: $count1"),
        p(cls := "count2", s"Count2: $count2"),
        button(
          onClick := (() => {
            logger.debug(
              "Button clicked - before updates",
              category = "Test",
              Map(
                "count1" -> count1.toString,
                "count2" -> count2.toString,
              ),
            )
            setCount1(count1 + 1)
            setCount2(count2 + 1)
            logger.debug(
              "Button clicked - after updates",
              category = "Test",
              Map(
                "count1" -> count1.toString,
                "count2" -> count2.toString,
              ),
            )
          }),
          "Increment Both",
        ),
      )
    }

    // Initial render
    val node = MultiStateComponent <> MultiStateProps()
    createDOM(node, container)

    // Initial assertions
    renderCount shouldBe 1

    // Click button
    click(container.querySelector("button"))

    // Create a "Future" that will complete after the next event loop
    eventually {
      renderCount shouldBe 2
      container.querySelector(".count1").textContent shouldBe "Count1: 1"
      container.querySelector(".count2").textContent shouldBe "Count2: 1"
    }
  }

  "BatchScheduler" should "handle state updates for unmounting components safely" in {
    val container = getContainer

    case class Props(onUnmount: () => Unit)

    def TestComponent(props: Props) = {
      val (count, setCount, _) = useState(0)

      useEffect(
        () => {
          // Schedule an update that will happen after unmount
          val timeout = js.timers.setTimeout(50) {
            setCount(count + 1)
          }

          // Cleanup
          () => {
            js.timers.clearTimeout(timeout)
            props.onUnmount()
          }
        },
        Seq(),
      )

      div(count.toString)
    }

    var unmounted = false
    val node      = TestComponent <> Props(() => unmounted = true)

    createDOM(node, container)

    // Force unmount
    reconcile(Some(node), None, container)

    eventually {
      unmounted shouldBe true
      // Verify container is empty
      container.childNodes.length shouldBe 0
    }
  }
}
