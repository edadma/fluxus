package io.github.edadma.fluxus.core.hooks

import io.github.edadma.fluxus.core.hooks.Hooks.useEffect
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import io.github.edadma.fluxus.core.types.*
import io.github.edadma.fluxus.core.util.IdGenerator
import io.github.edadma.fluxus.logging.Logger
import io.github.edadma.fluxus.logging.Logger.{Category, LogLevel}
import io.github.edadma.fluxus.error.HookValidationError
import io.github.edadma.fluxus.testing.BaseTest
import org.scalajs.dom

class HooksTimerTest extends BaseTest {
  // Helper to simulate a render using an existing or new instance
  def simulateRender(render: () => Unit, instance: Option[ComponentInstance] = None): ComponentInstance = {
    val componentInstance = instance.getOrElse(
      ComponentInstance(
        id = IdGenerator.nextComponentId(),
        component = (_: Any) => null,
        componentType = "TestComponent",
        props = (),
      ),
    )

    componentInstance.hookIndex = 0
    componentInstance.isRendering = true
    Hooks.setCurrentInstance(componentInstance)

    try {
      render()
    } finally {
      componentInstance.isRendering = false
      Hooks.clearCurrentInstance()
    }

    componentInstance
  }

  // Helper to run queued effects
  def runEffects(instance: ComponentInstance): Unit = {
    val effects = instance.effects
    instance.effects = Vector.empty
    effects.foreach(effect => effect())
  }

  "useEffect with timer" should "set up interval correctly" in withDebugLogging {
    var intervals                    = 0
    var cleanups                     = 0
    var currentInterval: Option[Int] = None

    // Mock window.setInterval
    val origSetInterval = dom.window.setInterval
    dom.window.setInterval = { (fn: () => Unit, timeout: Double) =>
      intervals += 1
      val id = 123 // Mock interval ID
      currentInterval = Some(id)
      id
    }

    // Mock window.clearInterval
    val origClearInterval = dom.window.clearInterval
    dom.window.clearInterval = { (id: Int) =>
      cleanups += 1
      currentInterval = None
    }

    try {
      // First render - should set up interval
      val instance = simulateRender { () =>
        useEffect(
          () => {
            val intervalId = dom.window.setInterval(() => {}, 1000)
            () => dom.window.clearInterval(intervalId)
          },
          Some(Seq()),
        )
      }

      intervals shouldBe 0 // Effect not run yet
      cleanups shouldBe 0
      currentInterval shouldBe None

      // Run the effects
      runEffects(instance)

      intervals shouldBe 1 // Interval should be set up
      cleanups shouldBe 0
      currentInterval shouldBe Some(123)

      // Simulate unmount
      instance.effects.foreach { effect =>
        // Run cleanup
        effect()
      }

      intervals shouldBe 1          // No new intervals
      cleanups shouldBe 1           // Cleanup should have run
      currentInterval shouldBe None // Interval should be cleared

    } finally {
      // Restore original functions
      dom.window.setInterval = origSetInterval
      dom.window.clearInterval = origClearInterval
    }
  }

  it should "clean up interval on re-render with changed deps" in withDebugLogging {
    var intervals = 0
    var cleanups  = 0
    var deps      = 0

    // Mock interval functions
    val origSetInterval   = dom.window.setInterval
    val origClearInterval = dom.window.clearInterval

    dom.window.setInterval = { (fn: () => Unit, timeout: Double) =>
      intervals += 1
      123
    }

    dom.window.clearInterval = { (id: Int) =>
      cleanups += 1
    }

    try {
      // First render
      val instance = simulateRender { () =>
        useEffect(
          () => {
            val intervalId = dom.window.setInterval(() => {}, 1000)
            () => dom.window.clearInterval(intervalId)
          },
          Some(Seq(deps)),
        )
      }

      runEffects(instance)
      intervals shouldBe 1
      cleanups shouldBe 0

      // Second render with changed deps
      deps = 1
      simulateRender(
        () => {
          useEffect(
            () => {
              val intervalId = dom.window.setInterval(() => {}, 1000)
              () => dom.window.clearInterval(intervalId)
            },
            Some(Seq(deps)),
          )
        },
        Some(instance),
      )

      runEffects(instance)
      intervals shouldBe 2 // New interval created
      cleanups shouldBe 1  // Old interval cleaned up

    } finally {
      dom.window.setInterval = origSetInterval
      dom.window.clearInterval = origClearInterval
    }
  }

  it should "not reset interval when deps haven't changed" in withDebugLogging {
    var intervals = 0
    var cleanups  = 0
    val deps      = Seq(1, 2, 3)

    // Mock interval functions
    val origSetInterval   = dom.window.setInterval
    val origClearInterval = dom.window.clearInterval

    dom.window.setInterval = { (fn: () => Unit, timeout: Double) =>
      intervals += 1
      123
    }

    dom.window.clearInterval = { (id: Int) =>
      cleanups += 1
    }

    try {
      // First render
      val instance = simulateRender { () =>
        useEffect(
          () => {
            val intervalId = dom.window.setInterval(() => {}, 1000)
            () => dom.window.clearInterval(intervalId)
          },
          Some(deps),
        )
      }

      runEffects(instance)
      intervals shouldBe 1
      cleanups shouldBe 0

      // Second render with same deps
      simulateRender(
        () => {
          useEffect(
            () => {
              val intervalId = dom.window.setInterval(() => {}, 1000)
              () => dom.window.clearInterval(intervalId)
            },
            Some(deps),
          )
        },
        Some(instance),
      )

      runEffects(instance)
      intervals shouldBe 1 // No new interval
      cleanups shouldBe 0  // No cleanup needed

    } finally {
      dom.window.setInterval = origSetInterval
      dom.window.clearInterval = origClearInterval
    }
  }
}
