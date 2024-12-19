package io.github.edadma.fluxus.examples

import io.github.edadma.fluxus.*

object CountersApp:
  def App: FluxusNode =
    div(
      cls := "min-h-screen p-4 bg-base-200",
      Counters <> (),
    )

  def Counters: () => FluxusNode = () =>
    // Track collection of counters
    val (counters, setCounters) = useState(Vector[Int]())

    // Track whether to show stats
    val (showStats, setShowStats) = useState(false)

    div(
      cls := "container mx-auto",

      // Header with controls
      div(
        cls := "flex justify-between items-center mb-4",
        h1(cls := "text-2xl font-bold", "Counter Collection"),
        div(
          cls := "space-x-2",
          button(
            cls     := "btn btn-primary",
            onClick := (() => setCounters(counters :+ 0)),
            "Add Counter",
          ),
          button(
            cls     := "btn btn-secondary",
            onClick := (() => setShowStats(!showStats)),
            if showStats then "Hide Stats" else "Show Stats",
          ),
        ),
      ),

      // Stats card (conditional render)
      if showStats && counters.nonEmpty then
        div(
          cls := "card bg-base-100 shadow-xl mb-4",
          div(
            cls := "card-body",
            h2(cls := "card-title", "Statistics"),
            ul(
              cls := "space-y-2",
              li(s"Total counters: ${counters.length}"),
              li(s"Sum of all counts: ${counters.sum}"),
              li(s"Average count: ${counters.sum.toDouble / counters.length}"),
              li(s"Max count: ${counters.max}"),
              li(s"Min count: ${counters.min}"),
            ),
          ),
        )
      else null,

      // Grid of counters
      div(
        cls := "grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4",
        counters.zipWithIndex.map { case (count, idx) =>
          Counter <> CounterProps(
            count = count,
            onIncrement = () => {
              val updated = counters.updated(idx, count + 1)
              setCounters(updated)
            },
            onDecrement = () => {
              val updated = counters.updated(idx, count - 1)
              setCounters(updated)
            },
            onRemove = () => {
              val updated = counters.patch(idx, Nil, 1)
              setCounters(updated)
            },
          )
        },
      ),
    )

  case class CounterProps(
      count: Int,
      onIncrement: () => Unit,
      onDecrement: () => Unit,
      onRemove: () => Unit,
  )

  def Counter: CounterProps => FluxusNode = props =>
    // Local state for hover effect needs to be declared outside the JSX-like structure
    val (isHovering, setHovering) = useState(false)

    div(
      cls := "card bg-base-100 shadow-xl",
      div(
        cls := "card-body",
        div(
          cls := "flex justify-between items-center",
          h2(
            cls := "card-title",
            "Counter",
          ),
          button(
            cls          := s"btn btn-circle btn-ghost ${if isHovering then "btn-error" else ""}",
            onClick      := props.onRemove,
            onMouseEnter := (() => setHovering(true)),
            onMouseLeave := (() => setHovering(false)),
            "×",
          ),
        ),
        div(
          cls := "flex items-center justify-center space-x-4 mt-4",
          button(
            cls     := "btn btn-circle",
            onClick := props.onDecrement,
            "−",
          ),
          span(
            cls := "text-4xl font-bold",
            props.count.toString,
          ),
          button(
            cls     := "btn btn-circle",
            onClick := props.onIncrement,
            "+",
          ),
        ),
      ),
    )
