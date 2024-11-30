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
    FluxusLogger.Props.debug("Timer rendering", Map("instance" -> RenderContext.currentInstance))

    val (time, setTime) = useState(js.Date.now().toLong / 1000)
    FluxusLogger.Props.debug("Timer after useState", Map("time" -> time))

    useEffect(
      () => {
        val interval = timers.setInterval(1000) {
          setTime(js.Date.now().toLong / 1000)
        }
        () => timers.clearInterval(interval)
      },
      Seq(),
    )

    FluxusLogger.Props.debug("Timer creating TimeDisplay", Map("timeProps" -> TimeProps(time)))

    div(
      h1("Timer Test"),
      component(TimeDisplay)(TimeProps(time)),
    )
}

def App(props: Product): FluxusNode =
  component(Timer)(TimeProps(0))
@main def run(): Unit = renderApp("app", App)
