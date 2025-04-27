package io.github.edadma.fluxus.testing

import io.github.edadma.fluxus.*
import io.github.edadma.fluxus.core.createDOM

class RawHtmlTests extends AnyDOMSpec {
  "rawHtml function" should "correctly render HTML content" in {
    val container   = getContainer
    val htmlContent = "<h1>Hello World</h1><p>This is <strong>raw HTML</strong> content.</p>"

    val node = div(
      cls := "wrapper",
      rawHtml(htmlContent),
    )

    createDOM(node, container)

    // Verify the HTML was rendered correctly
    val wrapper = container.querySelector(".wrapper")
    wrapper should not be null

    val h1 = wrapper.querySelector("h1")
    h1 should not be null
    h1.textContent shouldBe "Hello World"

    val p = wrapper.querySelector("p")
    p should not be null
    p.textContent shouldBe "This is raw HTML content."

    val strong = p.querySelector("strong")
    strong should not be null
    strong.textContent shouldBe "raw HTML"
  }

  it should "sanitize HTML by default" in {
    val container     = getContainer
    val maliciousHtml = """
      <div>Safe content</div>
      <script>alert('XSS attack');</script>
      <p onclick="alert('click attack')">Click me</p>
    """

    val node = div(
      rawHtml(maliciousHtml),
    )

    createDOM(node, container)

    // Verify script tags were removed
    container.querySelector("script") shouldBe null

    // Verify onclick was removed
    val p = container.querySelector("p")
    p should not be null
    p.hasAttribute("onclick") shouldBe false
  }

  it should "allow unsanitized HTML when specified" in {
    val container = getContainer
    // Just testing with event handlers, not script tags which might execute in test environment
    val htmlWithHandlers = """<button id="test-btn" onclick="console.log('clicked')">Click me</button>"""

    val node = div(
      rawHtml(htmlWithHandlers, sanitize = false),
    )

    createDOM(node, container)

    val button = container.querySelector("#test-btn")
    button should not be null
    button.hasAttribute("onclick") shouldBe true
  }
}
