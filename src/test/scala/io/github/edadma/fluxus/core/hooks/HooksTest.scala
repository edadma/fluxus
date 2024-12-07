package io.github.edadma.fluxus.core.hooks

import org.scalatest.flatspec.AnyFlatSpec
import io.github.edadma.fluxus.core.types.*
import io.github.edadma.fluxus.core.util.IdGenerator
import io.github.edadma.fluxus.logging.Logger.{Category, LogLevel}
import Hooks.useState
import io.github.edadma.fluxus.testing.BaseTest

class HooksTest extends BaseTest {
  // Helper to simulate a render using an existing or new instance
  def simulateRender(render: () => Unit, instance: Option[ComponentInstance] = None): ComponentInstance = {
    val componentInstance = instance.getOrElse(
      ComponentInstance(
        id = IdGenerator.nextComponentId(),
        component = (_: Any) => null, // We don't need actual nodes for hook testing
        componentType = "TestComponent",
        props = Map.empty,
      ),
    )

    // Reset hook index for new render
    componentInstance.hookIndex = 0

    // Start render phase
    componentInstance.isRendering = true
    Hooks.setCurrentInstance(componentInstance)

    try {
      // Run the render function
      render()
    } finally {
      // Cleanup after render
      componentInstance.isRendering = false
      Hooks.clearCurrentInstance()
    }

    componentInstance
  }

  // Helper to run queued effects
  def runEffects(instance: ComponentInstance): Unit = {
    val effects = instance.effects
    instance.effects = Vector.empty // Clear the queue before running
    effects.foreach { effect =>
      try {
        effect()
      } catch {
        case e: Throwable =>
          fail(s"Effect failed: ${e.getMessage}")
      }
    }
  }

  "useState" should "maintain consistent hook indexes across renders" in {
    var instance: ComponentInstance = null

    def component(props: Any): FluxusNode = {
      val (count, _) = useState(0)
      TextNode(count.toString, None, None, None)
    }

    instance = ComponentInstance(
      id = "test",
      component = component,
      componentType = "TestComponent",
      props = (),
      domNode = None,
    )

    // Initial render - should set up hook
    Hooks.setCurrentInstance(instance)
    val (_, setState1) = useState(0)
    val firstHookIndex = instance.hookIndex
    Hooks.clearCurrentInstance()

    // Get hook again - should not create new hook or change index
    Hooks.setCurrentInstance(instance)
    val (_, setState2)  = useState(0)
    val secondHookIndex = instance.hookIndex
    Hooks.clearCurrentInstance()

    // Verify hook indexes are the same
    firstHookIndex shouldBe secondHookIndex

    // Verify hooks array length didn't grow
    instance.hooks.length shouldBe 1
  }

  it should "prevent state updates during cleanup" in {
    var setCount: Option[Int => Unit] = None

    val instance = simulateRender { () =>
      val (_, setter) = Hooks.useState(0)
      setCount = Some(setter)
    }

    // The hook should be in the first position
    val hook = instance.hooks(0).asInstanceOf[StateHook[Int]]

    // Verify initial state
    hook.value shouldBe 0

    // Try to update during cleanup
    instance.isInCleanup = true
    setCount.foreach(_(5))

    // Verify state wasn't updated
    hook.value shouldBe 0
  }

  "useEffect" should "run effects after render" in {
    var effectRan  = false
    var cleanupRan = false

    // First render
    val instance = simulateRender { () =>
      Hooks.useEffect(() => {
        effectRan = true
        () => { cleanupRan = true }
      })
    }

    effectRan shouldBe false // Effect shouldn't run immediately
    cleanupRan shouldBe false

    // Run queued effects
    runEffects(instance)

    effectRan shouldBe true
    cleanupRan shouldBe false
  }

  it should "clean up effects properly" in {
    var effectCount  = 0
    var cleanupCount = 0
    var deps         = 0

    // First render with initial dependency
    val instance = simulateRender { () =>
      Hooks.useEffect(
        () => {
          effectCount += 1
          () => { cleanupCount += 1 }
        },
        Seq(deps),
      )
    }

    runEffects(instance)
    effectCount shouldBe 1
    cleanupCount shouldBe 0

    // Update dependency to trigger effect cleanup and re-run
    deps = 1
    simulateRender(
      () => {
        Hooks.useEffect(
          () => {
            effectCount += 1
            () => { cleanupCount += 1 }
          },
          Seq(deps),
        )
      },
      Some(instance),
    )

    runEffects(instance)
    effectCount shouldBe 2  // New effect should run
    cleanupCount shouldBe 1 // Old effect should be cleaned up
  }

  it should "skip effects when dependencies haven't changed" in {
    var effectCount = 0
    val deps        = Seq(1, 2, 3)

    // First render
    val instance = simulateRender { () =>
      Hooks.useEffect(
        () => {
          effectCount += 1
          () => {}
        },
        deps,
      )
    }

    runEffects(instance)
    effectCount shouldBe 1

    // Second render with same deps
    simulateRender(
      () => {
        Hooks.useEffect(
          () => {
            effectCount += 1
            () => {}
          },
          deps,
        )
      },
      Some(instance),
    )

    runEffects(instance)
    effectCount shouldBe 1 // Should not have increased due to unchanged deps
  }

  it should "handle effect errors gracefully" in {
    // First render with effect that throws
    val instance = simulateRender { () =>
      Hooks.useEffect(() => {
        throw new RuntimeException("Test error")
        () => {}
      })
    }

    runEffects(instance)
    instance.hasEffectError shouldBe true
  }

  it should "handle cleanup errors gracefully" in {
    var nextEffectRan = false

    // First render with cleanup that throws
    val instance = simulateRender { () =>
      Hooks.useEffect(() => {
        () => { throw new RuntimeException("Cleanup error") }
      })
    }

    runEffects(instance)

    // Second render to trigger cleanup
    simulateRender(
      () => {
        Hooks.useEffect(() => {
          nextEffectRan = true
          () => {}
        })
      },
      Some(instance),
    )

    runEffects(instance)
    nextEffectRan shouldBe true // Should still run even after cleanup error
  }

  "Multiple hooks" should "maintain correct ordering" in {
    var state1     = 0
    var state2     = ""
    var effect1Ran = false
    var effect2Ran = false

    val instance = simulateRender { () =>
      val (count, _) = Hooks.useState(42)
      val (text, _)  = Hooks.useState("test")
      state1 = count
      state2 = text

      Hooks.useEffect(() => {
        effect1Ran = true
        () => {}
      })

      Hooks.useEffect(() => {
        effect2Ran = true
        () => {}
      })
    }

    state1 shouldBe 42
    state2 shouldBe "test"

    runEffects(instance)
    effect1Ran shouldBe true
    effect2Ran shouldBe true
  }
}
