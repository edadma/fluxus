package io.github.edadma.fluxus

import scala.language.postfixOps
import Implicits.*
import scala.scalajs.js
import org.scalajs.dom

@main def run(): Unit = renderApp("app", SimpleTimer)

def SimpleTimer(props: Props): FluxusNode =
  println("SimpleTimer component rendering")

  val (time, setTime)       = useState(0.0)
  val (running, setRunning) = useState(false)

  println(s"SimpleTimer state: time=$time, running=$running")

  useEffect(
    () => {
      println(s"Effect function created with running=$running")

      if running then
        println(s"Starting timer with running=$running")
        val startTime = js.Date.now()
        var frameId   = 0

        // Break recursion by creating update function first
        def doUpdate(now: Double): Unit = {
          val elapsed = now - startTime
          println(s"Frame update: elapsed=$elapsed ms")
          setTime(elapsed)
          frameId = dom.window.requestAnimationFrame(doUpdate).toInt
        }

        // Convert to js.Function1 for requestAnimationFrame
        val jsUpdate: js.Function1[Double, Unit] = (now: Double) => doUpdate(now)

        println("Requesting first animation frame")
        frameId = dom.window.requestAnimationFrame(jsUpdate).toInt

        // Return cleanup
        () => {
          println(s"Cleanup called with frameId=$frameId")
          dom.window.cancelAnimationFrame(frameId)
        }
      else
        println(s"Effect running with running=${running} - not starting timer")
        () => println("No cleanup needed for non-running state")
    },
    Seq(running), // Make sure running is listed as a dependency
  )

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
              println(s"Button clicked - current running=$running, setting to ${!running}")
              setRunning(!running)
            }),
            if running then "Stop" else "Start",
          ),
        ),
      ),
    ),
  )

def formatTime(ms: Double): String =
  val totalSeconds = (ms / 1000).floor
  val minutes      = (totalSeconds / 60).toInt
  val seconds      = (totalSeconds % 60).toInt
  val milliseconds = (ms           % 1000).toInt
  f"$minutes%02d:$seconds%02d.$milliseconds%03d"
