package io.github.edadma.fluxus.testing

import io.github.edadma.fluxus.*
import io.github.edadma.fluxus.core.{ComponentInstance, createDOM, reconcile}

import scala.scalajs.js

class HookTest extends AnyDOMSpec {
  "useState hook" should "maintain state between renders" in {
    val container = getContainer
    case class TestProps()

    def TestComponent(props: TestProps): FluxusNode = {
      val (count, setCount) = useState(0)
      div(count.toString)
    }

    createDOM(TestComponent <> TestProps(), container)
    container.textContent shouldBe "0"
  }

  it should "maintain the same setter function between renders" in /*withDebugLogging(
    "maintain the same setter function between renders",
  )*/ {
    val container                               = getContainer
    var setterRef: Option[Int => Unit]          = None
    var renderCount                             = 0
    var lastInstance: Option[ComponentInstance] = None

    case class TestProps(trigger: Int)

    def TestComponent(props: TestProps): FluxusNode = {
      renderCount += 1
      val currentInstance = ComponentInstance.current

      logger.debug(
        "TestComponent rendering",
        category = "Test",
        Map(
          "renderCount"        -> renderCount.toString,
          "trigger"            -> props.trigger.toString,
          "instanceId"         -> currentInstance.map(_.id).getOrElse("none"),
          "previousInstanceId" -> lastInstance.map(_.id).getOrElse("none"),
          "sameInstance"       -> currentInstance.zip(lastInstance).map(_._1 eq _._2).getOrElse(false).toString,
        ),
      )

      lastInstance = currentInstance

      val (_, setter) = useState(0)
      if (setterRef.isEmpty) {
        setterRef = Some(setter)
        logger.debug("Stored initial setter", category = "Test")
      } else {
        logger.debug(
          "Comparing setters",
          category = "Test",
          Map(
            "setterRef"     -> setterRef.get.toString,
            "currentSetter" -> setter.toString,
            "equal"         -> (setter.asInstanceOf[AnyRef] eq setterRef.get.asInstanceOf[AnyRef]).toString,
          ),
        )
        assert(
          setter.asInstanceOf[AnyRef] eq setterRef.get.asInstanceOf[AnyRef],
          "Setter function reference changed between renders",
        )
      }
      div()
    }

    // First render
    val node = TestComponent <> TestProps(0)
    createDOM(node, container)
    renderCount shouldBe 1

    // Force a re-render with new props
    val newNode = TestComponent <> TestProps(1)
    reconcile(Some(node), Some(newNode), container)
    renderCount shouldBe 2
  }

  "useState hook" should "handle click events and state updates correctly" in /*withDebugLogging(
    "handle click events and state updates correctly",
  )*/ {
    val container   = getContainer
    var renderCount = 0

    case class CounterProps()

    def CounterComponent(props: CounterProps): FluxusNode = {
      renderCount += 1
      logger.debug(
        "Rendering CounterComponent",
        category = "Test",
        Map(
          "renderCount" -> renderCount.toString,
        ),
      )

      val (count, setCount) = useState(0)

      div(
        cls := "counter",
        p(cls := "count", s"Count: $count"),
        button(
          onClick := (() => {
            logger.debug(
              "Button clicked",
              category = "Test",
              Map(
                "currentCount" -> count.toString,
              ),
            )
            setCount(count + 1)
          }),
          "Increment",
        ),
      )
    }

    // Initial render
    val node = CounterComponent <> CounterProps()
    createDOM(node, container)

    // Initial assertions
    renderCount shouldBe 1
    container.querySelector(".count").textContent shouldBe "Count: 0"

    // Click button
    click(container.querySelector("button"))

    // Give React-style batching a chance to complete
    js.timers.setTimeout(0) {
      renderCount shouldBe 2
      container.querySelector(".count").textContent shouldBe "Count: 1"
    }
  }
}
