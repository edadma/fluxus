package io.github.edadma.fluxus.testing

import io.github.edadma.fluxus.*
import io.github.edadma.fluxus.core.{ComponentInstance, createDOM, reconcile}

import scala.scalajs.js

class HookTest extends AsyncDOMSpec {
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

  it should "maintain referential equality of setter functions" in /*withDebugLogging(
    "setter function stability",
  )*/ {
    val container            = getContainer
    var firstSetter: AnyRef  = null
    var secondSetter: AnyRef = null

    case class StabilityTestProps(value: Int)

    def StabilityTestComponent(props: StabilityTestProps): FluxusNode = {
      logger.debug(
        "Rendering StabilityTestComponent",
        category = "Test",
        Map("value" -> props.value.toString),
      )

      val (_, setter) = useState(0)

      // Store setter references to compare
      if (firstSetter == null) {
        firstSetter = setter.asInstanceOf[AnyRef]
        logger.debug(
          "Storing first setter reference",
          category = "Test",
          Map("setter" -> firstSetter.toString),
        )
      } else {
        secondSetter = setter.asInstanceOf[AnyRef]
        logger.debug(
          "Storing second setter reference",
          category = "Test",
          Map(
            "firstSetter"  -> firstSetter.toString,
            "secondSetter" -> secondSetter.toString,
            "areEqual"     -> (firstSetter eq setter.asInstanceOf[AnyRef]).toString,
          ),
        )
      }

      div()
    }

    // Initial render
    val firstNode = StabilityTestComponent <> StabilityTestProps(1)
    createDOM(firstNode, container)

    // Force a re-render with new props
    val secondNode = StabilityTestComponent <> StabilityTestProps(2)
    reconcile(Some(firstNode), Some(secondNode), container)

    // Check setter stability using reference equality
    withClue("Setter function reference should be identical across renders") {
      firstSetter should not be null
      secondSetter should not be null
      assert(firstSetter eq secondSetter, "Setter functions are not referentially equal")
    }
  }

  it should "handle functional updates correctly" in {
    val container   = getContainer
    var renderCount = 0

    case class CounterProps()

    def Counter(props: CounterProps): FluxusNode = {
      renderCount += 1
      val (count, setCount) = useState(0)

      div(
        p(cls := "count", s"Count: $count"),
        button(
          onClick := (() => {
            setCount(prev => prev + 1)
            setCount(prev => prev + 1)
          }),
          "Increment Twice",
        ),
      )
    }

    val node = Counter <> CounterProps()
    createDOM(node, container)

    container.querySelector(".count").textContent shouldBe "Count: 0"
    click(container.querySelector("button"))

    eventually {
      container.querySelector(".count").textContent shouldBe "Count: 2"
    }
  }

  it should "maintain multiple independent states in one component" in withDebugLogging(
    "multiple hooks stability",
  ) {
    val container   = getContainer
    var renderCount = 0

    case class MultiCounterProps()

    def MultiCounterComponent(props: MultiCounterProps): FluxusNode = {
      renderCount += 1
      logger.debug(
        "Rendering MultiCounterComponent",
        category = "Test",
        Map(
          "renderCount" -> renderCount.toString,
          "instance"    -> ComponentInstance.current.map(_.id).getOrElse("none"),
        ),
      )

      val (count1, setCount1) = useState(0)
      val (count2, setCount2) = useState(10)      // Different initial value
      val (text, setText)     = useState("hello") // Different type

      logger.debug(
        "Component state values",
        category = "Test",
        Map(
          "count1" -> count1.toString,
          "count2" -> count2.toString,
          "text"   -> text,
        ),
      )

      div(
        cls := "multi-counter",
        div(cls := "count1", s"Count1: $count1"),
        div(cls := "count2", s"Count2: $count2"),
        div(cls := "text", s"Text: $text"),
        button(
          cls := "inc1",
          onClick := (() => {
            logger.debug(
              "Incrementing count1",
              category = "Test",
              Map("currentValue" -> count1.toString),
            )
            setCount1(_ + 1)
          }),
          "Increment First",
        ),
        button(
          cls := "inc2",
          onClick := (() => {
            logger.debug(
              "Incrementing count2",
              category = "Test",
              Map("currentValue" -> count2.toString),
            )
            setCount2(_ + 1)
          }),
          "Increment Second",
        ),
        button(
          cls := "setText",
          onClick := (() => {
            logger.debug(
              "Updating text",
              category = "Test",
              Map("currentText" -> text),
            )
            setText(_ + "!")
          }),
          "Add Exclamation",
        ),
      )
    }

    // Initial render
    val node = MultiCounterComponent <> MultiCounterProps()
    createDOM(node, container)

    // Initial state
    renderCount shouldBe 1
    container.querySelector(".count1").textContent shouldBe "Count1: 0"
    container.querySelector(".count2").textContent shouldBe "Count2: 10"
    container.querySelector(".text").textContent shouldBe "Text: hello"

    // Click first counter
    click(container.querySelector(".inc1"))

    eventually {
      renderCount shouldBe 2
      container.querySelector(".count1").textContent shouldBe "Count1: 1"
      container.querySelector(".count2").textContent shouldBe "Count2: 10" // Unchanged
      container.querySelector(".text").textContent shouldBe "Text: hello"  // Unchanged
    }

    // Click second counter
    click(container.querySelector(".inc2"))

    eventually {
      renderCount shouldBe 3
      container.querySelector(".count1").textContent shouldBe "Count1: 1" // Unchanged
      container.querySelector(".count2").textContent shouldBe "Count2: 11"
      container.querySelector(".text").textContent shouldBe "Text: hello" // Unchanged
    }

    // Update text
//    click(container.querySelector(".setText"))
//
//    eventually {
//      renderCount shouldBe 4
//      container.querySelector(".count1").textContent shouldBe "Count1: 1"  // Unchanged
//      container.querySelector(".count2").textContent shouldBe "Count2: 11" // Unchanged
//      container.querySelector(".text").textContent shouldBe "Text: hello!"
//    }
  }

  it should "handle click events and state updates correctly" in /*withDebugLogging(
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
    eventually {
      renderCount shouldBe 2
      container.querySelector(".count").textContent shouldBe "Count: 1"
    }
  }
}
