package io.github.edadma.fluxus.examples

import io.github.edadma.fluxus.logging.Logger
import io.github.edadma.fluxus.logging.Logger.{Category, LogLevel}

@main def run(): Unit = {
  Logger.setLevel(LogLevel.DEBUG)
  val opId = Logger.nextOperationId

  Logger.info(Category.Component, "Starting test app", opId)

  val content =
    """
        |<div class="hero min-h-screen bg-base-200">
        |  <div class="hero-content text-center">
        |    <div class="max-w-md">
        |      <h1 class="text-5xl font-bold">Hello World</h1>
        |      <p class="py-6">This is a simple test app using DaisyUI styling.</p>
        |      <button class="btn btn-primary">Get Started</button>
        |    </div>
        |  </div>
        |</div>
      """.stripMargin

  // When the framework is ready, this will be replaced with proper component rendering
  val container = org.scalajs.dom.document.getElementById("app")
  container.innerHTML = content
}
