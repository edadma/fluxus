package io.github.edadma.fluxus.testing

import io.github.edadma.fluxus.*
import io.github.edadma.fluxus.core.*

class BatchSchedulerTest extends DOMSpec {
  "BatchScheduler" should "batch multiple state updates" in {
    val container   = getContainer
    var renderCount = 0

    case class MultiStateProps()

    def MultiStateComponent(props: MultiStateProps): FluxusNode = {
      renderCount += 1
      val (count1, setCount1) = useState(0)
      val (count2, setCount2) = useState(0)

      div(
        p(cls := "count1", s"Count1: $count1"),
        p(cls := "count2", s"Count2: $count2"),
        button(
          onClick := (() => {
            // These should be batched into a single update
            setCount1(count1 + 1)
            setCount2(count2 + 1)
          }),
          "Increment Both",
        ),
      )
    }

    // Initial render
    val node = MultiStateComponent <> MultiStateProps()
    createDOM(node, container)

    renderCount shouldBe 1
    container.querySelector(".count1").textContent shouldBe "Count1: 0"
    container.querySelector(".count2").textContent shouldBe "Count2: 0"

    // Trigger multiple updates
    click(container.querySelector("button"))

    // Should only render once for both updates
    renderCount shouldBe 2
    container.querySelector(".count1").textContent shouldBe "Count1: 1"
    container.querySelector(".count2").textContent shouldBe "Count2: 1"
  }
}
