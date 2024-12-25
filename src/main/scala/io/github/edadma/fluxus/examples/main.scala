package io.github.edadma.fluxus.examples

import io.github.edadma.fluxus.render

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExportTopLevel

@JSExportTopLevel("Babar")
class Foobaz(x: String) extends js.Object

@main def run(): Unit = render(MockServerTestApp.App, "app")
