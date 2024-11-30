package io.github.edadma.fluxus

import scala.language.postfixOps
import Implicits.*

import scala.scalajs.js
import scala.scalajs.js.timers

case class TimeProps(time: Int)

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
    useEffect(
      () => {
        val interval = timers.setInterval(1000) {
          // Just create new TimeDisplay with new props
          TimeDisplay(TimeProps(js.Date.now().toInt / 1000))
        }

        () => timers.clearInterval(interval)
      },
      Seq(),
    )

    div(
      h1("Timer Test"),
      TimeDisplay(TimeProps(js.Date.now().toInt / 1000)),
    )
}

def App(props: Props): FluxusNode =
  Timer(TimeProps(0))

@main def run(): Unit = renderApp("app", App)
