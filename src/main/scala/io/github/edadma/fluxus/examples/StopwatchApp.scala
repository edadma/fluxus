package io.github.edadma.fluxus.examples

import io.github.edadma.fluxus.api.*
import io.github.edadma.fluxus.core.hooks.Hooks.{useEffect, useState}
import io.github.edadma.fluxus.core.types.FluxusNode
import org.scalajs.dom
import org.scalajs.dom.Event

import scala.scalajs.js
import scala.scalajs.js.{Date, timers}

def now: Double = Date.now() / 1000

object StopwatchApp:
  def App: FluxusNode =
    val (running, setRunning) = useState(false)
    val (time, setTime)       = useState(0.0) // Time in seconds
    val (start, setStart)     = useState(now)

    useEffect(
      () => {
        if running then
          // Create interval using dom.window.setInterval
          val interval = dom.window.setInterval(
            () => {
              val currentTime = now - start
              setTime(currentTime)
            },
            10, // Update every 10ms for smooth display
          )

          // Return cleanup function
          () => dom.window.clearInterval(interval)
        else
          () => () // No cleanup needed if not running
      },
      Seq(running, start), // Add start to dependencies to handle resets correctly
    )

    def toggleRunning(e: Event): Unit =
      if !running then
        setStart(now - time) // Preserve accumulated time when resuming
      setRunning(!running)

    def resetTimer(e: Event): Unit =
      setRunning(false)
      setTime(0.0)
      setStart(now)

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
            button(
              cls     := "btn btn-secondary w-20",
              onClick := resetTimer,
              "Reset",
            ),
          ),
          MemoryStats <> (),
        ),
      ),
    )

  def MemoryStats: () => FluxusNode = () =>
    val (stats, setStats) = useState(Map[String, Double]())

    useEffect(
      () => {
        // Safe access to memory stats
        def updateMemoryStats(): Unit =
          if js.Dynamic.global.performance != null &&
            js.Dynamic.global.performance.memory != null
          then
            val mem = js.Dynamic.global.performance.memory.asInstanceOf[js.Dynamic]
            setStats(Map(
              "used"  -> (mem.usedJSHeapSize.asInstanceOf[Double] / (1024 * 1024)),
              "total" -> (mem.totalJSHeapSize.asInstanceOf[Double] / (1024 * 1024)),
              "limit" -> (mem.jsHeapSizeLimit.asInstanceOf[Double] / (1024 * 1024)),
            ))

        // Initial update
        updateMemoryStats()

        // Set up interval
        val intervalId = dom.window.setInterval(() => updateMemoryStats(), 1000)

        // Cleanup
        () => dom.window.clearInterval(intervalId)
      },
      Seq(), // Empty deps array - run once on mount
    )

    div(
      cls := "stats shadow w-full mt-4",
      div(
        cls := "stat",
        div(cls := "stat-title", "Memory Usage"),
        div(
          cls := "stat-value text-accent text-2xl",
          f"${stats.getOrElse("used", 0.0)}%.1f MB",
        ),
        div(
          cls := "stat-desc",
          f"Total: ${stats.getOrElse("total", 0.0)}%.1f MB / " +
            f"Limit: ${stats.getOrElse("limit", 0.0)}%.1f MB",
        ),
      ),
    )
