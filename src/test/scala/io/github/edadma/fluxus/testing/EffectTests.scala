package io.github.edadma.fluxus.testing

import io.github.edadma.fluxus.*
import io.github.edadma.fluxus.core.{ComponentInstance, createDOM, reconcile, diff, commit}
import org.scalajs.dom
import pprint.pprintln

class EffectTests extends AsyncDOMSpec {
  "useEffect" should "run effect after render and handle cleanup" in withDebugLogging(
    "run effect after render and handle cleanup",
  ) {
    val container  = getContainer
    var effectRan  = false
    var cleanupRan = false

    logger.debug(
      "Setting up test",
      category = "Test",
      Map(
        "effectRan"  -> effectRan.toString,
        "cleanupRan" -> cleanupRan.toString,
      ),
    )

    case class EffectTestProps()

    def EffectTestComponent(props: EffectTestProps) = {
      logger.debug(
        "Rendering EffectTestComponent",
        category = "Test",
        Map(
          "effectRan"  -> effectRan.toString,
          "cleanupRan" -> cleanupRan.toString,
          "instance"   -> ComponentInstance.current.map(_.id).getOrElse("none"),
        ),
      )

      useEffect(() => {
        logger.debug("Effect function running", category = "Test")
        effectRan = true
        // Return cleanup function
        () => {
          logger.debug("Cleanup function running", category = "Test")
          cleanupRan = true
        }
      })

      div("Test Component")
    }

    // Initial render
    createDOM(EffectTestComponent <> EffectTestProps(), container)

    eventually {
      logger.debug(
        "Checking effect execution",
        category = "Test",
        Map(
          "effectRan"  -> effectRan.toString,
          "cleanupRan" -> cleanupRan.toString,
        ),
      )

      // Effect should have run after initial render
      effectRan shouldBe true
      // Cleanup shouldn't have run yet
      cleanupRan shouldBe false
    }
      .flatMap { _ =>
        // Force cleanup by unmounting
        val ops = diff(Some(EffectTestComponent <> EffectTestProps()), None)

        commit(ops, container)

        eventually {
          // Cleanup should now have run
          cleanupRan shouldBe true
        }
      }
  }
}
