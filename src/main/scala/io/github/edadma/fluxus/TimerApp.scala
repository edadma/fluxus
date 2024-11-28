package io.github.edadma.fluxus

import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.{Date, timers}

@main def run(): Unit = renderApp("app", TimerApp)

def now: Double = Date.now() / 1000

def TimerApp(props: Props): FluxusNode =
  RenderTracker.trackRender("TimerApp") {
    val (running, setRunning) = useState(false)
    val (time, setTime)       = useState(0.0) // Time in seconds
    val (start, setStart)     = useState(now)

    useEffect(
      () => {
        if running then
          val interval = dom.window.setInterval(
            () => {
              setTime(now - start)
            },
            10,
          )

          () => dom.window.clearInterval(interval)
        else
          () => () // No cleanup needed if not running
      },
      Seq(running),
    )

    def toggleRunning(): Unit =
      if !running then
        setStart(now - time)

      setRunning(!running)

    def resetTimer(): Unit =
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
            cls := "stat-value seven-segment text-4xl my-4",
            formatTime(time),
          ),
          div(
            cls := "flex gap-2",
            button(
              cls := "btn btn-primary w-20",
              if running then "Stop" else "Start",
              onClick := (() => toggleRunning()),
            ),
            button(
              cls := "btn btn-secondary w-20",
              "Reset",
              onClick := (() => resetTimer()),
            ),
          ),
          MemoryStats(Map()),
        ),
      ),
    )
  }

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
