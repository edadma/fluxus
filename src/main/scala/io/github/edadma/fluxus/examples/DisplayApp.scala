package io.github.edadma.fluxus.examples

import io.github.edadma.fluxus.api.*
import io.github.edadma.fluxus.core.dom.DOMOperations
import io.github.edadma.fluxus.core.types.{ElementNode, FluxusNode}
import org.scalajs.dom

object DisplayApp:
  case class DisplayProps(message: String, color: String = "alert-primary")

  val Display: DisplayProps => FluxusNode =
    case DisplayProps(message, color) =>
      div(
        cls := s"alert $color shadow-lg",
        div(
          span(message),
        ),
      )

  // Main app component with no props
  def App: ElementNode =
    div(
      cls := "min-h-screen bg-base-200 p-8",
      div(
        cls := "card bg-base-100 shadow-xl max-w-md mx-auto",
        div(
          cls := "card-body",
          h1(
            cls := "card-title text-2xl font-bold text-center justify-center",
            "My Simple App",
          ),
          Display <> DisplayProps("Hello from Fluxus!"),
          Display <> DisplayProps("Another message", "alert-success"),
        ),
      ),
    )
