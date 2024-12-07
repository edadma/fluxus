package io.github.edadma.fluxus.examples

import io.github.edadma.fluxus.api.*
import io.github.edadma.fluxus.core.types.*
import io.github.edadma.fluxus.core.hooks.Hooks.*
import io.github.edadma.fluxus.logging.Logger
import io.github.edadma.fluxus.logging.Logger.Category
import org.scalajs.dom

object EffectTestApp:
  def Display: () => FluxusNode = () =>
    val opId = Logger.nextOperationId
    Logger.debug(Category.Component, "Display rendering", opId)

    val (message, setMessage) = useState("Initial")

    useEffect(
      () => {
        Logger.debug(Category.StateEffect, "Effect running", opId)
        setMessage("Updated")
        () => ()
      },
      Nil,
    )

    div(
      cls := "card w-96 bg-base-100 shadow-xl",
      div(
        cls := "card-body",
        h2(cls := "card-title", "Effect Test"),
        div(
          cls := "text-xl",
          message,
        ),
      ),
    )

  def App: FluxusNode =
    div(
      cls := "min-h-screen bg-base-200 flex items-center justify-center",
      Display <> (),
    )
