package io.github.edadma.fluxus

import scala.language.postfixOps
import Implicits.*
import scala.scalajs.js
import org.scalajs.dom

@main def run(): Unit = renderApp("app", SimpleTimer)

def SimpleTimer(props: Props): FluxusNode =
  val (time, setTime)       = useState(0.0)
  val (running, setRunning) = useState(false)

  useEffect(
    () => {
      println(s"Effect running - running state: $running") // Debug log

      if running then
        val startTime = js.Date.now()
        var lastTime  = startTime

        def updateFrame(now: Double): Unit =
          println(s"Frame update - time: ${now - startTime}") // Debug log
          setTime(now - startTime)
          lastTime = now
          dom.window.requestAnimationFrame(updateFrame)

        println("Starting animation frame") // Debug log
        val frameId = dom.window.requestAnimationFrame(updateFrame)

        // Return cleanup
        () => {
          println(s"Cleaning up effect - frameId: $frameId") // Debug log
          dom.window.cancelAnimationFrame(frameId)
        }
      else
        () => println("No cleanup needed") // Debug log
    },
    Seq(running),
  )

  def formatTime(ms: Double): String =
    val totalSeconds = ms / 1000
    val minutes      = (totalSeconds / 60).toInt
    val seconds      = (totalSeconds % 60).toInt
    val milliseconds = (ms           % 1000).toInt
    f"$minutes%02d:$seconds%02d.${milliseconds}%03d"

  div(
    cls := "min-h-screen bg-base-200 flex items-center justify-center p-4",
    div(
      cls := "card w-96 bg-base-100 shadow-xl",
      div(
        cls := "card-body",
        h2(cls := "card-title text-center mb-4", "Simple Timer Test"),
        div(
          cls := "text-4xl font-mono text-center mb-4",
          formatTime(time),
        ),
        div(
          cls := "flex justify-center",
          button(
            cls := s"btn ${if running then "btn-error" else "btn-success"}",
            onClick := (() => {
              println(s"Button clicked - setting running to ${!running}") // Debug log
              setRunning(!running)
            }),
            if running then "Stop" else "Start",
          ),
        ),
      ),
    ),
  )
