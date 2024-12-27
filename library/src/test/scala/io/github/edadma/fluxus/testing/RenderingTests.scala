package io.github.edadma.fluxus.testing

import io.github.edadma.fluxus.*
import io.github.edadma.fluxus.core.{ComponentInstance, commit, createDOM, diff, reconcile}
import org.scalajs.dom

import pprint.pprintln

class RenderingTests extends AnyDOMSpec {
  "ElementNode property binding" should "maintain checkbox state correctly after user interaction" in {
    val container = getContainer

    // Create an unchecked checkbox
    val node = input(
      typ     := "checkbox",
      checked := false,
    )
    createDOM(node, container)

    val checkbox      = container.querySelector("input")
    val checkboxInput = checkbox.asInstanceOf[dom.html.Input]

    // Verify initial state
    checkboxInput.checked shouldBe false

    // Simulate user clicking the checkbox
    click(checkbox)

    // After click, the property is true but attribute hasn't changed
    checkboxInput.checked shouldBe true
    checkbox.hasAttribute("checked") shouldBe false

    // Now try to reconcile with the same props
    val newNode = input(
      typ     := "checkbox",
      checked := false, // Same as original
    )

    reconcile(Some(node), Some(newNode), container)

    // This should fail because we're not respecting the user's interaction
    // We're trying to set the attribute back to false, but the property remains true
    checkboxInput.checked shouldBe false // This should fail
  }

  "createDOMNode" should "handle boolean attributes correctly" in {
    val container = getContainer

    // Test checked=true
    val checkedBox = input(
      typ      := "checkbox",
      disabled := true,
    )
    createDOM(checkedBox, container)
    val checkedElement = container.querySelector("input")
    checkedElement.hasAttribute("disabled") shouldBe true

    // Clear container
    container.innerHTML = ""

    // Test checked=false
    val uncheckedBox = input(
      typ      := "checkbox",
      disabled := false,
    )
    createDOM(uncheckedBox, container)
    val uncheckedElement = container.querySelector("input")
    uncheckedElement.hasAttribute("disabled") shouldBe false
  }

  "ElementNode property binding" should "handle form element properties correctly" in {
    val container = getContainer

    // Create a checkbox with checked=true via attribute
    val node = input(
      typ     := "checkbox",
      checked := true,
    )
    createDOM(node, container)

    // Get the DOM node
    val checkbox = container.querySelector("input")

    // Current behavior: getAttribute("checked") returns "true"
    // But we want the property to be set
    checkbox.asInstanceOf[dom.html.Input].checked shouldBe true // This will fail
  }
}
