package io.github.edadma.fluxus.examples

import io.github.edadma.fluxus.*
import com.raquo.airstream.state.Var
import com.raquo.airstream.core.Transaction

object SignalDemoApp {
  // Create a shared signal for our counter
  private val counterSignal =
    try {
      Var[Int](0)
    } catch {
      case e: Throwable =>
        logger.error(
          "Error creating counter signal",
          category = "SignalDemo",
          Map("error" -> e.toString),
        )
        throw e
    }

  private val multiplierSignal =
    try {
      Var[Int](1)
    } catch {
      case e: Throwable =>
        logger.error(
          "Error creating multiplier signal",
          category = "SignalDemo",
          Map("error" -> e.toString),
        )
        throw e
    }

  def App: FluxusNode =
    div(
      cls := "min-h-screen bg-base-200 p-4",
      div(
        cls := "container mx-auto max-w-4xl",

        // Header
        div(
          cls := "text-center mb-8",
          h1(cls := "text-4xl font-bold mb-2", "Signal Demo"),
          p(cls  := "text-lg opacity-75", "Demonstrating state sharing with Airstream signals"),
        ),

        // Main content grid
        div(
          cls := "grid grid-cols-1 md:grid-cols-2 gap-4",

          // Counter controls card
          Controls <> (),

          // Stats card
          Stats <> (),

          // Additional displays
          MultiplyDisplay <> (),
          HistoryDisplay <> (),
        ),
      ),
    )

  def Controls: () => FluxusNode = () => {
    val count = useSignal(counterSignal)

    div(
      cls := "card bg-base-100 shadow-xl",
      div(
        cls := "card-body",
        h2(cls := "card-title", "Controls"),
        div(
          cls := "flex flex-col items-center gap-4",
          div(
            cls := "text-6xl font-bold font-dseg7modernmini",
            count.toString,
          ),
          div(
            cls := "flex gap-2",
            button(
              cls     := "btn btn-primary",
              onClick := (() => Transaction(_ => counterSignal.set(count - 1))),
              "−",
            ),
            button(
              cls     := "btn btn-primary",
              onClick := (() => Transaction(_ => counterSignal.set(count + 1))),
              "+",
            ),
          ),
          button(
            cls     := "btn btn-secondary w-full",
            onClick := (() => Transaction(_ => counterSignal.set(0))),
            "Reset",
          ),
        ),
      ),
    )
  }

  def Stats: () => FluxusNode = () => {
    val count = useSignal(counterSignal)

    div(
      cls := "card bg-base-100 shadow-xl",
      div(
        cls := "card-body",
        h2(cls := "card-title", "Statistics"),
        div(
          cls := "stats shadow",
          div(
            cls := "stat",
            div(cls := "stat-title", "Current Value"),
            div(cls := "stat-value", count.toString),
            div(
              cls := "stat-desc",
              if (count > 0) "Positive" else if (count < 0) "Negative" else "Zero",
            ),
          ),
          div(
            cls := "stat",
            div(cls := "stat-title", "Squared"),
            div(cls := "stat-value", (count * count).toString),
            div(cls := "stat-desc", "n²"),
          ),
        ),
      ),
    )
  }

  def MultiplyDisplay: () => FluxusNode = () => {
    val count      = useSignal(counterSignal)
    val multiplier = useSignal(multiplierSignal)

    div(
      cls := "card bg-base-100 shadow-xl",
      div(
        cls := "card-body",
        h2(cls := "card-title", "Multiplier"),
        div(
          cls := "flex flex-col gap-4",
          div(
            cls := "flex items-center gap-4",
            span(cls := "text-xl", count.toString),
            span(cls := "text-xl", "×"),
            input(
              typ    := "number",
              cls    := "input input-bordered w-24",
              value_ := multiplier.toString,
              onInput := ((e: org.scalajs.dom.Event) =>
                Transaction(_ => multiplierSignal.set(e.target.asInstanceOf[org.scalajs.dom.html.Input].value.toInt))
              ),
            ),
            span(cls := "text-xl", "="),
            span(cls := "text-xl font-bold", (count * multiplier).toString),
          ),
          input(
            typ    := "range",
            cls    := "range",
            min    := "1",
            max    := "10",
            value_ := multiplier.toString,
            onInput := ((e: org.scalajs.dom.Event) =>
              multiplierSignal.set(e.target.asInstanceOf[org.scalajs.dom.html.Input].value.toInt)
            ),
          ),
          div(
            cls := "w-full flex justify-between text-xs px-2",
            (1 to 10).map(n => div(n.toString)),
          ),
        ),
      ),
    )
  }

  def HistoryDisplay: () => FluxusNode = () => {
    val count                               = useSignal(counterSignal)
    val (lastCount, setLastCount, _)        = useState(count)
    val (graphPoints, _, updateGraphPoints) = useState(Vector[Int]())

    useEffect(
      () => {
        // Only update history if the value actually changed
        if (count != lastCount) {
          setLastCount(count)
          updateGraphPoints(p => (p :+ count).takeRight(20))
        }
        ()
      },
      Seq(count),
    )

    div(
      cls := "card bg-base-100 shadow-xl",
      div(
        cls := "card-body",
        h2(cls := "card-title mb-4", "Live Graph"),
        if (graphPoints.isEmpty)
          p(cls := "text-center opacity-50", "No data yet")
        else {
          val width   = 300
          val height  = 100
          val padding = 10

          // Find min/max for scaling
          val min   = graphPoints.min
          val max   = graphPoints.max
          val range = if (max == min) 1 else max - min

          // Scale points to fit height
          val scaled = graphPoints.zipWithIndex.map { case (y, x) =>
            // When there's only one point, put it in the middle
            val scaledX = if (graphPoints.length == 1)
              width / 2
            else
              padding + (x * (width - 2 * padding) / (graphPoints.length - 1).toDouble)

            val scaledY = height - (padding + ((y - min) * (height - 2 * padding) / range.toDouble))
            s"${scaledX},${scaledY}"
          }

          svg(
            cls     := "w-full",
            viewBox := s"0 0 $width $height",
            polyline(
              fill        := "none",
              stroke      := "#FF00CC",
              strokeWidth := "2",
              points      := scaled.mkString(" "),
            ),
          )
        },
      ),
    )
  }
}
