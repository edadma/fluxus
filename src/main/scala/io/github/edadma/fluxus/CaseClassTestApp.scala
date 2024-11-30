package io.github.edadma.fluxus

import scala.language.postfixOps
import Implicits.*

import scala.scalajs.js
import scala.scalajs.js.timers

case class TimeProps(time: Long)

// Child that just displays time, no state
val TimeDisplay: FC[TimeProps] = {
  case TimeProps(time) =>
    FluxusLogger.Props.debug(
      "TimeDisplay rendered",
      Map(
        "time"      -> time,
        "propsType" -> "TimeProps",
      ),
    )
    div(s"Time elapsed: $time seconds")
}

// Parent that uses setInterval to update child props
val Timer: FC[TimeProps] = {
  case TimeProps(_) =>
    val (time, setTime) = useState(js.Date.now().toLong / 1000)

    useEffect(
      () => {
        val interval = timers.setInterval(1000) {
          setTime(js.Date.now().toLong / 1000) // Update state to trigger re-render
        }
        () => timers.clearInterval(interval)
      },
      Seq(),
    )

    div(
      h1("Timer Test"),
      TimeDisplay(TimeProps(time)), // Use the time from state
    )
}

def App(props: Product): FluxusNode =
  Timer(TimeProps(0))
@main def run(): Unit = renderApp("app", App)
