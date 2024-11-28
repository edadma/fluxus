package io.github.edadma.fluxus

import org.scalajs.dom

@main def run(): Unit = renderApp("app", TimerApp)

def TimerApp(props: Props): FluxusNode =
  RenderTracker.trackRender("TimerApp") {
    val (running, setRunning) = useState(false)
    val (time, setTime)       = useState(0.0) // Time in seconds

    // Effect to update time every 10ms (hundredths of a second) when running
    useEffect(
      () => {
        if running then
          val interval = dom.window.setInterval(
            () => {
              setTime(time + 0.01)
            },
            10,
          ) // 10ms interval
          () => dom.window.clearInterval(interval)
        else
          () => () // No cleanup needed if not running
      },
      Seq(running, time),
    ) // Dependencies: running state, time

    def toggleRunning(): Unit = setRunning(!running)
    def resetTimer(): Unit = {
      setRunning(false)
      setTime(0.0)
    }

    // Helper to format time
    def formatTime(totalSeconds: Double): String = {
      val hours      = (totalSeconds / 3600).toInt
      val minutes    = ((totalSeconds % 3600) / 60).toInt
      val seconds    = (totalSeconds  % 60).toInt
      val hundredths = ((totalSeconds % 1) * 100).toInt
      f"$hours%02d:$minutes%02d:$seconds%02d.$hundredths%02d"
    }

    // DaisyUI styled components
    div(
      cls := "flex flex-col items-center gap-4 p-4",
      div(
        cls := "text-4xl font-mono",
        formatTime(time), // Display formatted time
      ),
      div(
        cls := "flex gap-2",
        button(
          cls := "btn btn-primary w-20",
          if running then "Stop" else "Start",
          onClick := (() => toggleRunning()),
        ),
        button(
          cls := "btn btn-secondary",
          "Reset",
          onClick := (() => resetTimer()),
        ),
      ),
    )
  }
