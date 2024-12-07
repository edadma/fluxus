package io.github.edadma.fluxus.core.dom

import io.github.edadma.fluxus.core.hooks.Hooks
import io.github.edadma.fluxus.core.types.*
import io.github.edadma.fluxus.testing.DOMSpec
import io.github.edadma.fluxus.logging.Logger
import io.github.edadma.fluxus.logging.Logger.{Category, LogLevel}
import io.github.edadma.fluxus.api.NoProps

class EffectStateTest extends DOMSpec {
  "Main component with useEffect" should "update state immediately after mount" in {
    var renderCount = 0
    var effectRan   = false

    def App(props: NoProps): FluxusNode = {
      renderCount += 1
      val opId = Logger.nextOperationId

      Logger.debug(Category.Component, "App rendering", opId, Map("renderCount" -> renderCount))

      val (message, setMessage) = Hooks.useState("Initial")

      Hooks.useEffect(() => {
        Logger.debug(Category.StateEffect, "Effect running", opId)
        effectRan = true
        setMessage("Updated")
        () => ()
      })

      TextNode(message, None, None, None)
    }

    // Create and mount directly
    val component = Component.create(
      render = App,
      props = NoProps(),
      opId = Logger.nextOperationId,
      name = Some("App"),
    )

    DOMOperations.mount(component, getContainer)

    val finalOpId = Logger.nextOperationId

    Logger.debug(
      Category.Component,
      "Main component test state after mount",
      finalOpId,
      Map(
        "renderCount" -> renderCount,
        "effectRan"   -> effectRan,
      ),
    )

    Logger.debug(
      Category.VirtualDOM,
      "Final DOM content",
      finalOpId,
      Map("content" -> getContainer.textContent),
    )

    effectRan shouldBe true
    renderCount shouldBe 2 // Should have rendered twice
    getContainer.textContent shouldBe "Updated"
  }

  "Child component with useEffect" should "update state immediately after mount" in withDebugLogging {
    var childRenderCount = 0
    var childEffectRan   = false

    // The child component
    def Display(props: NoProps): FluxusNode = {
      childRenderCount += 1
      val opId = Logger.nextOperationId

      Logger.debug(Category.Component, "Display rendering", opId, Map("renderCount" -> childRenderCount))

      val (message, setMessage) = Hooks.useState("Initial")

      Hooks.useEffect(() => {
        Logger.debug(Category.StateEffect, "Child effect running", opId)
        childEffectRan = true
        setMessage("Updated")
        () => ()
      })

      TextNode(message, None, None, None)
    }

    // The parent component that renders Display
    def Parent(props: NoProps): FluxusNode = {
      val opId = Logger.nextOperationId
      Logger.debug(Category.Component, "Parent rendering", opId)

      ElementNode(
        tag = "div",
        props = Map.empty,
        events = Map.empty,
        children = Vector(
          Component.create(
            render = Display,
            props = NoProps(),
            opId = opId,
            name = Some("Display"),
          ),
        ),
        parent = None,
        domNode = None,
        key = None,
      )
    }

    // Create and mount the parent
    val component = Component.create(
      render = Parent,
      props = NoProps(),
      opId = Logger.nextOperationId,
      name = Some("Parent"),
    )

    DOMOperations.mount(component, getContainer)

    val finalOpId = Logger.nextOperationId

    Logger.debug(
      Category.Component,
      "Child component test state after mount",
      finalOpId,
      Map(
        "childRenderCount" -> childRenderCount,
        "childEffectRan"   -> childEffectRan,
      ),
    )

    Logger.debug(
      Category.VirtualDOM,
      "Final DOM content",
      finalOpId,
      Map("content" -> getContainer.textContent),
    )

    childEffectRan shouldBe true
    childRenderCount shouldBe 2 // Should have rendered twice
    getContainer.textContent shouldBe "Updated"
  }
}
