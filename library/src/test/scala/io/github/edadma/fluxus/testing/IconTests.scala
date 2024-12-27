package io.github.edadma.fluxus.testing

import io.github.edadma.fluxus.*
import io.github.edadma.fluxus.core.*

class IconTests extends AnyDOMSpec {
  "Icon component" should "render a Lucide icon correctly" in withDebugLogging("render a Lucide icon correctly") {
    val container = getContainer

    // Test with Camera icon from Lucide
    val node = Icon(LucideIcons.Camera)("size" -> 48, "color" -> "#FF00CC")
    createDOM(node, container)

    // Verify SVG was created with correct attributes
    val svg = container.querySelector("svg")
    svg should not be null
    svg.getAttribute("width") shouldBe "48"
    svg.getAttribute("stroke") shouldBe "#FF00CC"

    // Verify paths were created
    svg.querySelectorAll("path").length should be > 0
  }
}
