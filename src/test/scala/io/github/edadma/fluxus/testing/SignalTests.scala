package io.github.edadma.fluxus.testing

import com.raquo.airstream.state.Var
import io.github.edadma.fluxus.*
//import org.scalatest.time.{Millis, Span}

class SignalTests extends AsyncDOMSpec {
//  override implicit val patienceConfig: PatienceConfig = PatienceConfig(
//    timeout = Span(1000, Millis), // Increase timeout
//    interval = Span(50, Millis),  // Check more frequently
//  )

  "useSignal" should "update component when signal changes" in /*withDebugLogging(
    "update component when signal changes",
  )*/ {
    val container = getContainer
    val signal    = Var(0)

    def Counter = () => {
      val count = useSignal[Int](signal)
      logger.debug(
        "Rendering Counter",
        category = "SignalTest",
        Map("count" -> count.toString),
      )
      div(
        cls := "counter",
        s"Count: $count",
      )
    }

    // Mount component
    render(Counter <> (), container)

    // Test initial render
    eventually {
      container.querySelector(".counter").textContent shouldBe "Count: 0"
    }.flatMap { _ =>
      // Update signal externally
      logger.debug("Setting signal to 1", category = "SignalTest")
      signal.set(1)

      eventually {
        container.querySelector(".counter").textContent shouldBe "Count: 1"
      }
    }
  }

  it should "allow multiple components to share state" in withDebugLogging(
    "allow multiple components to share state",
  ) {
    import com.raquo.airstream.state.Var

    val container     = getContainer
    val counterSignal = Var(0) // Shared signal

    def CounterDisplay(props: CounterDisplayProps) = {
      val count = useSignal[Int](counterSignal)

      logger.debug(
        "Rendering CounterDisplay",
        category = "SignalTest",
        Map(
          "count" -> count.toString,
        ),
      )

      div(
        cls := "counter-display",
        s"Count: $count",
      )
    }
    case class CounterDisplayProps()

    def SignalApp = () => {
      div(
        CounterDisplay <> CounterDisplayProps(),
        CounterDisplay <> CounterDisplayProps(),
        button(
          onClick := (() => {
            logger.debug(
              "Increment clicked",
              category = "SignalTest",
            )
            counterSignal.update(_ + 1)
          }),
          "Increment",
        ),
      )
    }

    render(SignalApp <> (), container)

    // Test initial state
    eventually {
      val displays = container.querySelectorAll(".counter-display")
      displays.length shouldBe 2
      displays.item(0).textContent shouldBe "Count: 0"
      displays.item(1).textContent shouldBe "Count: 0"
    }.flatMap { _ =>
      // Test UI update
      logger.debug("Clicking increment button", category = "SignalTest")
      click(container.querySelector("button"))

      eventually {
        val displays = container.querySelectorAll(".counter-display")
        displays.item(0).textContent shouldBe "Count: 1"
        displays.item(1).textContent shouldBe "Count: 1"
      }
    }.flatMap { _ =>
      // Test external signal update
      logger.debug("Setting signal to 5", category = "SignalTest")
      counterSignal.set(5)

      eventually {
        val displays = container.querySelectorAll(".counter-display")
        displays.item(0).textContent shouldBe "Count: 5"
        displays.item(1).textContent shouldBe "Count: 5"
      }
    }
  }
}
