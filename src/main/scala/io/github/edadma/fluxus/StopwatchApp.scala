package io.github.edadma.fluxus

import scala.language.postfixOps
import Implicits.*
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.timers
import language.deprecated.symbolLiterals

//@main def run(): Unit = renderApp("app", StopwatchApp)

// Main application component
def StopwatchApp(props: Props): FluxusNode =
  RenderTracker.trackRender("StopwatchApp"):
    val (time, setTime)         = useState(0.0) // Time in milliseconds
    val (running, setRunning)   = useState(false)
    val (captures, setCaptures) = useState(List[Double]())
    val (hovering, setHovering) = useState(false)

    // Effect with fixed timestamp handling
    useEffect(
      () => {
        if running && !hovering then
          println(s"Starting stopwatch with running=$running")
          var startTimestamp: Double = 0 // Will be set in first frame

          var frameId = 0

          def doUpdate(now: Double): Unit = {
            if startTimestamp == 0 then
              startTimestamp = now // Capture first frame timestamp
              println(s"Setting initial timestamp: $startTimestamp")

            val elapsed = now - startTimestamp
            println(s"Frame update: elapsed=$elapsed ms")
            setTime(elapsed)
            frameId = dom.window.requestAnimationFrame((t: Double) => doUpdate(t)).toInt
          }

          println("Requesting first animation frame")
          frameId = dom.window.requestAnimationFrame((t: Double) => doUpdate(t)).toInt

          // Return cleanup
          () => {
            println(s"Cleanup called with frameId=$frameId")
            dom.window.cancelAnimationFrame(frameId)
          }
        else
          println(s"Effect not starting timer: running=$running, hovering=$hovering")
          () => println("No cleanup needed for non-running state")
      },
      Seq(running, hovering), // Dependencies
    )

    def formatTime(ms: Double): String =
      val totalSeconds = (ms / 1000).floor
      val minutes      = (totalSeconds / 60).toInt
      val seconds      = (totalSeconds % 60).toInt
      val milliseconds = (ms           % 1000).toInt
      f"$minutes%02d:$seconds%02d.$milliseconds%03d"

    div(
      cls := "min-h-screen bg-base-200 flex items-center justify-center p-4",
      div(
        cls := "card w-96 bg-base-100 shadow-xl",
        div(
          cls := "card-body",
          h2(cls := "card-title text-center mb-4", "Memory Test Stopwatch"),
          div(
            cls := "stats shadow mb-4",
            div(
              cls := "stat",
              div(cls := "stat-title", "Elapsed Time"),
              div(
                cls := "stat-value text-primary text-center font-mono",
                onMouseEnter := (() => {
                  if running then
                    setHovering(true)
                    setCaptures(time +: captures)
                }),
                onMouseLeave := (() => setHovering(false)),
                formatTime(time),
              ),
              div(
                cls := "stat-desc text-center",
                if hovering then "Paused - Move mouse away to resume"
                else "Hover to capture time",
              ),
            ),
          ),
          div(
            cls := "flex justify-center gap-2 mb-4",
            button(
              cls     := s"btn ${if running then "btn-error" else "btn-success"}",
              onClick := (() => setRunning(!running)),
              if running then "Stop" else "Start",
            ),
            button(
              cls := "btn btn-warning",
              onClick := (() => {
                setTime(0.0)
                setCaptures(List())
                setRunning(false)
              }),
              "Reset",
            ),
          ),
          MemoryStats(),
          div(
            cls      := "collapse collapse-arrow bg-base-200",
            tabindex := "0",
            div(
              cls := "collapse-title font-medium",
              s"Captured Times (${captures.length})",
            ),
            div(
              cls := "collapse-content",
              div(
                cls := "max-h-48 overflow-y-auto",
                if captures.isEmpty then
                  p(cls := "text-sm text-base-content/60", "No captured times yet")
                else
                  ul(
                    cls := "space-y-1",
                    captures.zipWithIndex.map { case (capturedTime, idx) =>
                      li(
                        cls  := "text-sm bg-base-100 p-2 rounded",
                        'key := idx.toString,
                        s"#${idx + 1}: ${formatTime(capturedTime)}",
                      )
                    },
                  ),
              ),
            ),
          ),
        ),
      ),
    )

// Separate component for memory statistics
private val MemoryStats: FC = (props: Props) =>
  RenderTracker.trackRender("MemoryStats"):
    val (stats, setStats) = useState(Map[String, Double]())

    useEffect(
      () => {
        val intervalId = timers.setInterval(1000) {
          val mem = js.Dynamic.global.performance.memory.asInstanceOf[js.Dynamic]
          setStats(Map(
            "used"  -> (mem.usedJSHeapSize.asInstanceOf[Double] / (1024 * 1024)),
            "total" -> (mem.totalJSHeapSize.asInstanceOf[Double] / (1024 * 1024)),
            "limit" -> (mem.jsHeapSizeLimit.asInstanceOf[Double] / (1024 * 1024)),
          ))
        }

        () => timers.clearInterval(intervalId)
      },
      Seq(),
    )

    div(
      cls := "stats shadow w-full mb-4",
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