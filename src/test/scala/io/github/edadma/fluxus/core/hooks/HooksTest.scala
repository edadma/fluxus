package io.github.edadma.fluxus.core.hooks

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import io.github.edadma.fluxus.core.types._
import io.github.edadma.fluxus.core.util.IdGenerator
import io.github.edadma.fluxus.logging.Logger
import io.github.edadma.fluxus.logging.Logger.{Category, LogLevel}

class HooksTest extends AnyFlatSpec with Matchers {
  // Set detailed logging for debugging
  Logger.setLevel(LogLevel.DEBUG)

  // Helper to simulate a component render and return the component instance
  def simulateRender(render: () => Unit): ComponentInstance = {
    val instance = ComponentInstance(
      id = IdGenerator.nextComponentId(),
      component = (_: Any) => null, // We don't need actual nodes for hook testing
      componentType = "TestComponent",
      props = Map.empty,
    )

    // Start render phase
    instance.isRendering = true
    Hooks.setCurrentInstance(instance)

    try {
      // Run the render function
      render()
    } finally {
      // Cleanup after render
      instance.isRendering = false
      Hooks.clearCurrentInstance()
    }

    instance
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

  "useState" should "maintain state between renders" in {
    var count                         = 0
    var setCount: Option[Int => Unit] = None

    // First render
    val instance = simulateRender { () =>
      val (value, setter) = Hooks.useState(0)
      count = value
      setCount = Some(setter)
    }

    count shouldBe 0

    // Update state
    setCount.foreach(_(5))
    instance.needsRender shouldBe true

    // Second render
    simulateRender { () =>
      val (value, _) = Hooks.useState(0)
      count = value
    }

    count shouldBe 5
  }

  it should "prevent state updates during cleanup" in {
    var setCount: Option[Int => Unit] = None

    val instance = simulateRender { () =>
      val (_, setter) = Hooks.useState(0)
      setCount = Some(setter)
    }

    // Try to update during cleanup
    instance.isInCleanup = true
    setCount.foreach(_(5))
    instance.needsRender shouldBe false
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

    effectRan shouldBe false
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
        Some(Seq(deps)),
      )
    }

    runEffects(instance)
    effectCount shouldBe 1
    cleanupCount shouldBe 0

    // Update dependency to trigger effect cleanup and re-run
    deps = 1
    simulateRender { () =>
      Hooks.useEffect(
        () => {
          effectCount += 1
          () => { cleanupCount += 1 }
        },
        Some(Seq(deps)),
      )
    }

    runEffects(instance)
    effectCount shouldBe 2
    cleanupCount shouldBe 1
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
        Some(deps),
      )
    }

    runEffects(instance)
    effectCount shouldBe 1

    // Second render with same deps
    simulateRender { () =>
      Hooks.useEffect(
        () => {
          effectCount += 1
          () => {}
        },
        Some(deps),
      )
    }

    runEffects(instance)
    effectCount shouldBe 1 // Should not have increased
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
    simulateRender { () =>
      Hooks.useEffect(() => {
        nextEffectRan = true
        () => {}
      })
    }

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
