package io.github.edadma.fluxus.core.types

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import io.github.edadma.fluxus.error.PropValidationError
import testing.BaseTest

class ComponentTest extends AnyFlatSpec with Matchers with BaseTest {
  case class TestProps(value: String)

  def TestComponent(props: TestProps): FluxusNode =
    TextNode(props.value, None, None, None)

  "Component.create" should "create a valid component node" in {
    val props = TestProps("test")
    val node  = Component.create(TestComponent, props, None, 1, Some("TestComponent"))

    node shouldBe a[ComponentNode]
    node.props shouldBe props // Compare directly to case class
    node.instance.isDefined shouldBe true
    node.instance.get.componentType shouldBe "TestComponent"
  }

  it should "handle optional keys" in {
    val node = Component.create(TestComponent, TestProps("test"), Some("testKey"), 1)
    node.key shouldBe Some("testKey")
  }

  it should "initialize component state" in {
    val node     = Component.create(TestComponent, TestProps("test"), None, 1)
    val instance = node.instance.get

    instance.hooks shouldBe empty
    instance.hookIndex shouldBe 0
    instance.effects shouldBe empty
    instance.needsRender shouldBe true
    instance.isRendering shouldBe false
  }

  it should "reject invalid props" in {
    val invalidComponent = (x: String) => TextNode(x, None, None, None)

    assertThrows[PropValidationError] {
      Component.create(invalidComponent, "test", None, 1)
    }
  }
}
