package io.github.edadma.fluxus.examples

import io.github.edadma.fluxus.api.*
import io.github.edadma.fluxus.core.FluxusNode
import io.github.edadma.fluxus.{cls, div, h1, span}

object DisplayApp:
  def App: FluxusNode =
    div(
      cls := "min-h-screen bg-base-200 p-8",
      Header <> (),
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

  def Header: () => FluxusNode = () =>
    div(
      cls := "navbar bg-base-100 mb-10",
      div(
        cls := "flex-1",
        span(cls := "text-xl font-bold", "Demo App"),
      ),
    )

  case class DisplayProps(message: String, color: String = "alert-primary")

  def Display: DisplayProps => FluxusNode =
    case DisplayProps(message, color) =>
      div(
        cls := s"alert $color shadow-lg",
        div(
          span(message),
        ),
      )
