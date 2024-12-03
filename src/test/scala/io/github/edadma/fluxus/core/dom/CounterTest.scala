package io.github.edadma.fluxus.dom

import io.github.edadma.fluxus.testing.DOMSpec
import io.github.edadma.fluxus.core.types.Component
import io.github.edadma.fluxus.core.dom.DOMOperations
import io.github.edadma.fluxus.logging.Logger
import io.github.edadma.fluxus.logging.Logger.LogLevel
import io.github.edadma.fluxus.examples.CounterApp.{Counter, CounterProps}

class CounterTest extends DOMSpec {
  Logger.setLevel(LogLevel.DEBUG)

  "Counter component" should "render initial state" in {
    val container = document.getElementById("app")
    val counter = Component.create(
      Counter,
      CounterProps(),
      None,
      1,
      Some("Counter"),
    )

    DOMOperations.mount(counter, container)

    container.textContent should include("0")
  }

  it should "increment counter when + button clicked" in {
    val container = document.getElementById("app")
    val counter = Component.create(
      Counter,
      CounterProps(),
      None,
      1,
      Some("Counter"),
    )

    DOMOperations.mount(counter, container)

    val button = container.querySelector("button")
    button.click()

    container.textContent should include("1")
  }

  it should "decrement counter when - button clicked" in {
    val container = document.getElementById("app")
    val counter = Component.create(
      Counter,
      CounterProps(),
      None,
      1,
      Some("Counter"),
    )

    DOMOperations.mount(counter, container)

    val button = container.querySelectorAll("button")(1)
    button.click()

    container.textContent should include("-1")
  }
}
