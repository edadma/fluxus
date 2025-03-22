package io.github.edadma.fluxus.testing

import io.github.edadma.fluxus.*
import io.github.edadma.fluxus.core.{createDOM, reconcile}
import org.scalajs.dom

class UseMemoTests extends AsyncDOMSpec {
  "useMemo hook" should "cache computed value and avoid recomputation" in /*withDebugLogging(
    "cache computed value and avoid recomputation",
  )*/ {
    val container    = getContainer
    var computeCount = 0

    case class TestProps(value: Int, triggerRender: Int)

    def TestComponent(props: TestProps): FluxusNode = {
      // Use memo to compute a value based on props.value
      val computed = useMemo(
        () => {
          computeCount += 1
          props.value * 2
        },
        Seq(props.value), // Only depend on value, not triggerRender
      )

      div(
        cls := "result",
        s"Computed: $computed, Triggers: ${props.triggerRender}",
      )
    }

    // Initial render with value = 5
    val initialNode = TestComponent <> TestProps(5, 0)
    createDOM(initialNode, container)

    container.querySelector(".result").textContent shouldBe "Computed: 10, Triggers: 0"
    computeCount shouldBe 1

    // Update with same value but different triggerRender
    val sameValueNode = TestComponent <> TestProps(5, 1)
    reconcile(Some(initialNode), Some(sameValueNode), container)

    eventually {
      // Value should be the same, not recomputed
      container.querySelector(".result").textContent shouldBe "Computed: 10, Triggers: 1"
      computeCount shouldBe 1 // Shouldn't have recomputed
    }
      .flatMap { _ =>
        // Update with different value
        val differentValueNode = TestComponent <> TestProps(10, 2)
        reconcile(Some(sameValueNode), Some(differentValueNode), container)

        eventually {
          // Value should be updated and recomputed
          container.querySelector(".result").textContent shouldBe "Computed: 20, Triggers: 2"
          computeCount shouldBe 2 // Should have recomputed
        }
      }
  }

//  it should "handle complex dependency objects correctly" in /*withDebugLogging(
//    "handle complex dependency objects",
//  )*/ {
//    val container    = getContainer
//    var computeCount = 0
//
//    case class ComplexDep(id: String, value: Int)
//    case class ComplexProps(dep: ComplexDep, unrelated: String)
//
//    def ComplexTestComponent(props: ComplexProps): FluxusNode = {
//      val computed = useMemo(
//        () => {
//          computeCount += 1
//          s"${props.dep.id}-${props.dep.value * 2}"
//        },
//        Seq(props.dep), // Only depend on dep object
//      )
//
//      div(
//        cls := "complex-result",
//        s"Computed: $computed, Unrelated: ${props.unrelated}",
//      )
//    }
//
//    // Initial render
//    val initialDep  = ComplexDep("test", 5)
//    val initialNode = ComplexTestComponent <> ComplexProps(initialDep, "first")
//    createDOM(initialNode, container)
//
//    container.querySelector(".complex-result").textContent shouldBe "Computed: test-10, Unrelated: first"
//    computeCount shouldBe 1
//
//    // Update unrelated prop
//    val sameDepNode = ComplexTestComponent <> ComplexProps(initialDep, "second")
//    reconcile(Some(initialNode), Some(sameDepNode), container)
//
//    eventually {
//      // Value should be the same, not recomputed
//      container.querySelector(".complex-result").textContent shouldBe "Computed: test-10, Unrelated: second"
//      computeCount shouldBe 1 // Shouldn't have recomputed
//    }
//      .flatMap { _ =>
//        // Update with different dep object but same values
//        val sameDep          = ComplexDep("test", 5) // Same values but different object reference
//        val differentDepNode = ComplexTestComponent <> ComplexProps(sameDep, "third")
//        reconcile(Some(sameDepNode), Some(differentDepNode), container)
//
//        eventually {
//          // Should recompute because object reference changed, even though values are the same
//          container.querySelector(".complex-result").textContent shouldBe "Computed: test-10, Unrelated: third"
//          computeCount shouldBe 2 // Should have recomputed due to object reference change
//        }
//          .flatMap { _ =>
//            // Update with clearly different values
//            val differentDep       = ComplexDep("other", 10)
//            val differentValueNode = ComplexTestComponent <> ComplexProps(differentDep, "fourth")
//            reconcile(Some(differentDepNode), Some(differentValueNode), container)
//
//            eventually {
//              // Value should be updated and recomputed
//              container.querySelector(".complex-result").textContent shouldBe "Computed: other-20, Unrelated: fourth"
//              computeCount shouldBe 3 // Should have recomputed
//            }
//          }
//      }
//  }
//
//  it should "handle empty or null dependencies correctly" in withDebugLogging(
//    "handle empty or null dependencies correctly",
//  ) {
//    val container             = getContainer
//    var emptyDepsComputeCount = 0
//    var nullDepsComputeCount  = 0
//
//    case class DepsTestProps(value: Int)
//
//    def EmptyDepsComponent(props: DepsTestProps): FluxusNode = {
//      // Use empty deps array: should recompute on every render
//      val computed = useMemo(
//        () => {
//          emptyDepsComputeCount += 1
//          props.value * 2
//        },
//        Seq(), // Empty deps array
//      )
//
//      div(
//        cls := "empty-deps",
//        s"Computed with empty deps: $computed",
//      )
//    }
//
//    def NullDepsComponent(props: DepsTestProps): FluxusNode = {
//      // Use null deps: should recompute on every render
//      val computed = useMemo(
//        () => {
//          nullDepsComputeCount += 1
//          props.value * 3
//        },
//        null, // Null deps
//      )
//
//      div(
//        cls := "null-deps",
//        s"Computed with null deps: $computed",
//      )
//    }
//
//    // Test with empty deps
//    val emptyDepsNode = EmptyDepsComponent <> DepsTestProps(5)
//    createDOM(emptyDepsNode, container)
//
//    emptyDepsComputeCount shouldBe 1
//    container.querySelector(".empty-deps").textContent shouldBe "Computed with empty deps: 10"
//
//    // Re-render with same props
//    val emptyDepsNode2 = EmptyDepsComponent <> DepsTestProps(5)
//    reconcile(Some(emptyDepsNode), Some(emptyDepsNode2), container)
//
//    eventually {
//      emptyDepsComputeCount shouldBe 1 // Shouldn't recompute with empty deps array
//    }
//      .flatMap { _ =>
//        // Clean up
//        container.innerHTML = ""
//
//        // Test with null deps
//        val nullDepsNode = NullDepsComponent <> DepsTestProps(5)
//        createDOM(nullDepsNode, container)
//
//        nullDepsComputeCount shouldBe 1
//        container.querySelector(".null-deps").textContent shouldBe "Computed with null deps: 15"
//
//        // Re-render with same props
//        val nullDepsNode2 = NullDepsComponent <> DepsTestProps(5)
//        reconcile(Some(nullDepsNode), Some(nullDepsNode2), container)
//
//        eventually {
//          nullDepsComputeCount shouldBe 2 // Should recompute with null deps
//        }
//      }
//  }

  it should "handle multiple useMemo calls in the same component" in /*withDebugLogging(
    "handle multiple useMemo calls",
  )*/ {
    val container     = getContainer
    var compute1Count = 0
    var compute2Count = 0

    case class MultiMemoProps(value1: Int, value2: String)

    def MultiMemoComponent(props: MultiMemoProps): FluxusNode = {
      // First memo depending on value1
      val computed1 = useMemo(
        () => {
          compute1Count += 1
          props.value1 * 2
        },
        Seq(props.value1),
      )

      // Second memo depending on value2
      val computed2 = useMemo(
        () => {
          compute2Count += 1
          s"${props.value2}-processed"
        },
        Seq(props.value2),
      )

      div(
        cls := "multi-memo",
        div(cls := "result1", s"Computed1: $computed1"),
        div(cls := "result2", s"Computed2: $computed2"),
      )
    }

    // Initial render
    val initialNode = MultiMemoComponent <> MultiMemoProps(5, "test")
    createDOM(initialNode, container)

    compute1Count shouldBe 1
    compute2Count shouldBe 1
    container.querySelector(".result1").textContent shouldBe "Computed1: 10"
    container.querySelector(".result2").textContent shouldBe "Computed2: test-processed"

    // Update only first value
    val updateValue1Node = MultiMemoComponent <> MultiMemoProps(10, "test")
    reconcile(Some(initialNode), Some(updateValue1Node), container)

    eventually {
      compute1Count shouldBe 2 // Should have recomputed value1
      compute2Count shouldBe 1 // Should not have recomputed value2
      container.querySelector(".result1").textContent shouldBe "Computed1: 20"
      container.querySelector(".result2").textContent shouldBe "Computed2: test-processed"
    }
      .flatMap { _ =>
        // Update only second value
        val updateValue2Node = MultiMemoComponent <> MultiMemoProps(10, "changed")
        reconcile(Some(updateValue1Node), Some(updateValue2Node), container)

        eventually {
          compute1Count shouldBe 2 // Should not have recomputed value1
          compute2Count shouldBe 2 // Should have recomputed value2
          container.querySelector(".result1").textContent shouldBe "Computed1: 20"
          container.querySelector(".result2").textContent shouldBe "Computed2: changed-processed"
        }
          .flatMap { _ =>
            // Update both values
            val updateBothNode = MultiMemoComponent <> MultiMemoProps(15, "both")
            reconcile(Some(updateValue2Node), Some(updateBothNode), container)

            eventually {
              compute1Count shouldBe 3 // Should have recomputed value1
              compute2Count shouldBe 3 // Should have recomputed value2
              container.querySelector(".result1").textContent shouldBe "Computed1: 30"
              container.querySelector(".result2").textContent shouldBe "Computed2: both-processed"
            }
          }
      }
  }

  it should "maintain hook consistency when useMemo is conditionally called" in {
    val container    = getContainer
    var computeCount = 0

    case class ConditionalMemoProps(condition: Boolean, value: Int)

    // This should throw an error because hooks must be called in same order
    def ConditionalMemoComponent(props: ConditionalMemoProps): FluxusNode = {
      // First hook (always present)
      val (state, setState, _) = useState(0)

      // Conditionally call useMemo - THIS WILL BREAK HOOK RULES
      val computed = if (props.condition) {
        useMemo(
          () => {
            computeCount += 1
            props.value * 2
          },
          Seq(props.value),
        )
      } else {
        props.value // Direct value without memo
      }

      div(
        cls := "conditional-memo",
        s"State: $state, Computed: $computed",
        button(
          onClick := (() => setState(state + 1)),
          "Increment",
        ),
      )
    }

    // Initial render with condition true
    val initialNode = ConditionalMemoComponent <> ConditionalMemoProps(true, 5)

    val error = intercept[Error] {
      createDOM(initialNode, container)
      // Force a re-render with condition false, which should break hook rules
      val updatedNode = ConditionalMemoComponent <> ConditionalMemoProps(false, 5)
      reconcile(Some(initialNode), Some(updatedNode), container)
    }

    // Should throw an error about hooks
    error.getMessage should include("Hook")
  }
}
