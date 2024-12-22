package io.github.edadma.fluxus.testing

import io.github.edadma.fluxus.*
import io.github.edadma.fluxus.core.{ComponentInstance, createDOM, reconcile, diff, commit}
import org.scalajs.dom
import pprint.pprintln

class EffectTests extends AsyncDOMSpec {
  "useEffect" should "run effect after render and handle cleanup" in /*withDebugLogging(
    "run effect after render and handle cleanup",
  )*/ {
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
    val node = EffectTestComponent <> EffectTestProps()
    createDOM(node, container)

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
        val ops = diff(Some(node), None)

        commit(ops, container)

        eventually {
          // Cleanup should now have run
          cleanupRan shouldBe true
        }
      }
  }

  it should "re-run effect when dependencies change" in withDebugLogging("re-run effect when dependencies change") {
    val container   = getContainer
    var effectCount = 0

    // Props with a value that we'll change
    case class DependencyTestProps(value: Int)

    def DependencyTestComponent(props: DependencyTestProps): FluxusNode = {
      logger.debug(
        "Rendering DependencyTest component",
        category = "Test",
        Map(
          "value"       -> props.value.toString,
          "effectCount" -> effectCount.toString,
          "instance"    -> ComponentInstance.current.map(_.id).getOrElse("none"),
        ),
      )

      useEffect(
        () => {
          logger.debug(
            "Effect running",
            category = "Test",
            Map("value" -> props.value.toString),
          )

          effectCount += 1
          () => () // Empty cleanup to test only effect execution
        },
        Seq(props.value),
      )

      div(props.value.toString)
    }

    // Initial render
    val node = DependencyTestComponent <> DependencyTestProps(1)
    createDOM(node, container)

    eventually {
      // Effect should run once after initial render
      effectCount shouldBe 1
      container.textContent shouldBe "1"
    }
      .flatMap { _ =>
        logger.debug(
          "Before reconciliation",
          category = "Test",
          Map(
            "oldNode"     -> node.toString,
            "oldInstance" -> node.instance.map(_.id).getOrElse("none"),
            "oldProps"    -> node.props.toString,
            "effectCount" -> effectCount.toString,
          ),
        )

        // Update with new value
        val newNode = DependencyTestComponent <> DependencyTestProps(2)

        logger.debug(
          "Created new node",
          category = "Test",
          Map(
            "newNode"  -> newNode.toString,
            "newProps" -> newNode.props.toString,
          ),
        )

        val ops = reconcile(Some(node), Some(newNode), container)

        logger.debug(
          "Reconciliation operations",
          category = "Test",
          Map("ops" -> ops.mkString(", ")),
        )

        eventually {
          // Effect should run again due to dependency change
          effectCount shouldBe 2
          container.textContent shouldBe "2"
        }
      }
      .flatMap { _ =>
        // Update with same value
        val sameNode = DependencyTestComponent <> DependencyTestProps(2)
        reconcile(Some(node), Some(sameNode), container)

        eventually {
          // Effect should not run again since dependency didn't change
          effectCount shouldBe 2
          container.textContent shouldBe "2"
        }
      }
  }
}
