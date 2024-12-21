package io.github.edadma.fluxus.testing

import io.github.edadma.fluxus.*
import io.github.edadma.fluxus.core.{ComponentInstance, createDOM, reconcile}

class RenderingTests extends AnyDOMSpec {
  "createDOMNode" should "handle boolean attributes correctly for checkboxes" in {
    val container = getContainer

    // Test checked=true
    val checkedBox = input(
      typ     := "checkbox",
      checked := true,
    )
    createDOM(checkedBox, container)
    val checkedElement = container.querySelector("input")
    checkedElement.hasAttribute("checked") shouldBe true

    // Clear container
    container.innerHTML = ""

    // Test checked=false
    val uncheckedBox = input(
      typ     := "checkbox",
      checked := false,
    )
    createDOM(uncheckedBox, container)
    val uncheckedElement = container.querySelector("input")
    uncheckedElement.hasAttribute("checked") shouldBe false
  }
}
