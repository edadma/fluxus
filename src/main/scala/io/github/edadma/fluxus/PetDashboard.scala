package io.github.edadma.fluxus

import language.postfixOps
import language.deprecated.symbolLiterals

import Implicits.*

@main def run(): Unit = renderApp("app", PetDashboard)

// Main App Component (no props)
def PetDashboard(props: Props): FluxusNode =
  val (lastFed, setLastFed) = useState("Never")

  div(
    cls := "min-h-screen bg-base-200 p-8",
    div(
      cls := "max-w-2xl mx-auto",
      // Header (component with no props)
      DashboardHeader(),

      // Status cards (components with props)
      div(
        cls := "grid grid-cols-1 md:grid-cols-2 gap-4 mt-6",
        PetCard(
          'name    := "Whiskers",
          'species := "Cat",
          'mood    := "Grumpy",
          'onFeed  := (() => setLastFed("Just now")),
        ),
        PetCard(
          'name    := "Rover",
          'species := "Dog",
          'mood    := "Excited",
          'onFeed  := (() => setLastFed("Just now")),
        ),
      ),

      // Last fed indicator
      div(
        cls := "mt-6 text-center text-sm text-base-content/60",
        s"Last pet fed: $lastFed",
      ),
    ),
  )

// Component without props
val DashboardHeader: FluxusComponent = (_: Props) =>
  div(
    cls := "text-center",
    h1(
      cls := "text-4xl font-bold text-primary",
      "Pet Status Dashboard",
    ),
    p(
      cls := "mt-2 text-base-content/70",
      "Keep track of your pets' moods and feeding schedule",
    ),
  )

// Component with props
val PetCard: FluxusComponent = (props: Props) =>
  val name    = props("name").toString
  val species = props("species").toString
  val mood    = props("mood").toString
  val onFeed  = props("onFeed").asInstanceOf[() => Unit]

  div(
    cls := "card bg-base-100 shadow-xl",
    div(
      cls := "card-body",
      h2(
        cls := "card-title",
        name,
        span(
          cls := "badge badge-secondary",
          species,
        ),
      ),
      p(s"Current mood: $mood"),
      div(
        cls := "card-actions justify-end",
        button(
          cls     := "btn btn-primary",
          onClick := onFeed,
          "Feed",
        ),
      ),
    ),
  )
