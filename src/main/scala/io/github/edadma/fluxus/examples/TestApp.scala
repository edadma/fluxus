package io.github.edadma.fluxus.examples

import io.github.edadma.fluxus.logging.Logger
import io.github.edadma.fluxus.logging.Logger.{Category, LogLevel}
import io.github.edadma.fluxus.api.Element._
import io.github.edadma.fluxus.core.dom.DOMOperations

@main def run(): Unit = {
  Logger.setLevel(LogLevel.DEBUG)
  val opId = Logger.nextOperationId

  Logger.info(Category.Component, "Starting test app", opId)

  // Create a simple UI using our Element DSL
  val content =
    div(
      cls := "hero min-h-screen bg-base-200",
      div(
        cls := "hero-content text-center",
        div(
          cls := "max-w-md",
          h1(cls     := "text-5xl font-bold", "Hello World"),
          p(cls      := "py-6", "This is a simple test app using Fluxus and DaisyUI styling."),
          button(cls := "btn btn-primary", "Get Started"),
        ),
      ),
    )

  // Get container and mount
  val container = org.scalajs.dom.document.getElementById("app")
  DOMOperations.mount(content, container)
}
