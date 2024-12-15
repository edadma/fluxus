package io.github.edadma.fluxus.testing

import io.github.edadma.fluxus.*
import io.github.edadma.fluxus.core.{ComponentInstance, createDOM, reconcile}

class ComponentInstanceTest extends AnyDOMSpec {
  "ComponentInstance" should "maintain stable identity through re-renders" in {
    val container                                   = getContainer
    var capturedInstance: Option[ComponentInstance] = None

    case class Props(value: String)

    def TestComponent(props: Props) = {
      // Capture the actual instance
      capturedInstance = ComponentInstance.current

      div(props.value)
    }

    // Initial render
    val oldNode = TestComponent <> Props("first")
    createDOM(oldNode, container)

    val firstInstance = oldNode.instance.get
    capturedInstance shouldBe Some(firstInstance)

    // Re-render with new props
    val newNode = TestComponent <> Props("second")
    reconcile(Some(oldNode), Some(newNode), container)

    // The new node should have been given the original instance
    newNode.instance shouldBe Some(firstInstance)
    // And the captured instance during render should match
    capturedInstance shouldBe Some(firstInstance)
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

  "ComponentInstance" should "properly scope instance context during nested renders" in {
    val container                               = getContainer
    var outerCapture: Option[ComponentInstance] = None
    var innerCapture: Option[ComponentInstance] = None

    case class InnerProps(onRender: ComponentInstance => Unit)
    case class OuterProps(onRender: ComponentInstance => Unit)

    // Store single instances of the component functions
    val InnerComponent = (props: InnerProps) => {
      props.onRender(ComponentInstance.current.get)
      div("inner")
    }

    val OuterComponent = (props: OuterProps) => {
      props.onRender(ComponentInstance.current.get)
      div(
        InnerComponent <> InnerProps(instance => innerCapture = Some(instance)),
      )
    }

    // Initial render
    val node = OuterComponent <> OuterProps(instance => outerCapture = Some(instance))
    createDOM(node, container)

    // Verify correct instance scoping
    innerCapture.isDefined shouldBe true
    outerCapture.isDefined shouldBe true
    innerCapture should not be outerCapture

    // Verify component relationships
    innerCapture.get.componentType should startWith("InnerProps")
    outerCapture.get.componentType should startWith("OuterProps")
  }
}
