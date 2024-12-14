package io.github.edadma.fluxus.testing

import io.github.edadma.fluxus.*
import io.github.edadma.fluxus.core.{ComponentInstance, createDOM, reconcile}

class ComponentInstanceTest extends DOMSpec {

  "ComponentInstance" should "maintain stable identity through re-renders" in {
    val container          = getContainer
    var instanceId: String = ""

    case class Props(value: String)

    def TestComponent(props: Props) = {
      // Capture instance ID during render
      instanceId = ComponentInstance.current.map(_.id).getOrElse("none")

      div(props.value)
    }

    // Initial render
    val oldNode = TestComponent <> Props("first")
    createDOM(oldNode, container)
    val firstId = instanceId

    // Re-render
    val newNode = TestComponent <> Props("second")
    reconcile(Some(oldNode), Some(newNode), container)

    // Instance should be reused
    instanceId shouldBe firstId
  }

  it should "handle nested component instances correctly" in {
    val container        = getContainer
    var parentId: String = ""
    var childId: String  = ""

    case class ChildProps(label: String)
    case class ParentProps(childLabel: String)

    def ChildComponent(props: ChildProps) = {
      childId = ComponentInstance.current.map(_.id).getOrElse("none")
      div(props.label)
    }

    def ParentComponent(props: ParentProps) = {
      parentId = ComponentInstance.current.map(_.id).getOrElse("none")
      div(
        "Parent",
        ChildComponent <> ChildProps(props.childLabel),
      )
    }

    // Initial render
    val node = ParentComponent <> ParentProps("child")
    createDOM(node, container)

    // IDs should be different
    parentId should not be childId

    // Both should have valid IDs
    parentId should not be "none"
    childId should not be "none"
  }

  it should "properly scope instance context" in {
    var outerInstance: Option[ComponentInstance] = None
    var innerInstance: Option[ComponentInstance] = None
    var afterInstance: Option[ComponentInstance] = None

    val instance1 = ComponentInstance(componentType = "Test1")
    val instance2 = ComponentInstance(componentType = "Test2")

    outerInstance = ComponentInstance.current

    ComponentInstance.withInstance(instance1) {
      innerInstance = ComponentInstance.current

      ComponentInstance.withInstance(instance2) {
        // Nested instance should be instance2
        ComponentInstance.current shouldBe Some(instance2)
      }

      // Should restore to instance1
      ComponentInstance.current shouldBe Some(instance1)
    }

    afterInstance = ComponentInstance.current

    // Verify context restoration
    outerInstance shouldBe None
    innerInstance shouldBe Some(instance1)
    afterInstance shouldBe None
  }
}
