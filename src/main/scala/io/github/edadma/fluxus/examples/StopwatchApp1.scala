package io.github.edadma.fluxus.examples

import io.github.edadma.fluxus.api.*
import io.github.edadma.fluxus.core.hooks.Hooks.{useEffect, useState}
import io.github.edadma.fluxus.core.types.FluxusNode
import org.scalajs.dom
import org.scalajs.dom.Event

import scala.scalajs.js
import scala.scalajs.js.{Date, timers}

def now: Double = Date.now() / 1000

object StopwatchApp1:
  def App: FluxusNode =
    val (running, setRunning) = useState(false)
    val (time, setTime)       = useState(0.0) // Time in seconds

    useEffect(
      () => {
        val interval = dom.window.setInterval(
          () => {
            setTime(_ + .01)
          },
          10, // Update every 10ms for smooth display
        )

        () => dom.window.clearInterval(interval)
      },
      Nil,
    )

    def toggleRunning(e: Event): Unit =
      setRunning(!running)

    // Helper to format time
    def formatTime(totalSeconds: Double): String =
      val hours      = (totalSeconds / 3600).toInt % 24
      val minutes    = ((totalSeconds              % 3600) / 60).toInt
      val seconds    = (totalSeconds               % 60).toInt
      val hundredths = ((totalSeconds              % 1) * 100).toInt

      f"$hours%02d:$minutes%02d:$seconds%02d.$hundredths%02d"

    div(
      cls := "min-h-screen bg-base-200 flex items-center justify-center p-4",
      div(
        cls := "card w-96 bg-base-100 shadow-xl",
        div(
          cls := "card-body items-center text-center",
          h2(cls := "card-title", "Stopwatch"),
          div(
            cls := "stat-value font-dseg7modernmini my-4",
            formatTime(time),
          ),
          div(
            cls := "flex gap-2",
            button(
              cls     := "btn btn-primary w-20",
              onClick := toggleRunning,
              if running then "Stop" else "Start",
            ),
          ),
        ),
      ),
    )
