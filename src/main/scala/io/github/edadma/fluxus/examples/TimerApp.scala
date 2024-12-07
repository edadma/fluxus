package io.github.edadma.fluxus.examples

import io.github.edadma.fluxus.api.*
import io.github.edadma.fluxus.core.types.*
import io.github.edadma.fluxus.core.hooks.Hooks.*
import io.github.edadma.fluxus.logging.Logger
import io.github.edadma.fluxus.logging.Logger.{Category, LogLevel}
import org.scalajs.dom

import scala.scalajs.js

object TimerApp:
  def App: FluxusNode =
    val opId = Logger.nextOperationId
    Logger.debug(Category.Component, "Rendering Timer component", opId)

    val (seconds, setSeconds) = useState(0)

    // Set up timer effect with proper function conversion
    useEffect(
      () => {
        Logger.debug(Category.StateEffect, "Setting up timer effect", opId)

        val callback = () => {
          Logger.debug(Category.StateEffect, "Timer tick", opId)
          setSeconds(prev => prev + 1)
        }

        val intervalId = dom.window.setInterval(callback, 1000)

        // Cleanup function
        () => {
          Logger.debug(Category.StateEffect, "Cleaning up timer", opId)
          dom.window.clearInterval(intervalId)
        }
      },
      Nil,
    ) // Empty deps array means run once on mount

    // Format time as mm:ss
    val minutes    = seconds / 60
    val secs       = seconds % 60
    val timeString = f"$minutes%02d:$secs%02d"

    div(
      cls := "flex flex-col items-center justify-center min-h-screen bg-base-200",
      div(
        cls := "card w-96 bg-base-100 shadow-xl",
        div(
          cls := "card-body items-center text-center",
          h2(
            cls := "card-title text-3xl mb-4",
            "Timer Test",
          ),
          div(
            cls := "stats shadow",
            div(
              cls := "stat",
              div(
                cls := "stat-title",
                "Time Elapsed",
              ),
              div(
                cls := "stat-value font-mono text-4xl",
                timeString,
              ),
              div(
                cls := "stat-desc",
                s"$seconds total seconds",
              ),
            ),
          ),
        ),
      ),
    )
