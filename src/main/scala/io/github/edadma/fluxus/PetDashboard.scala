package io.github.edadma.fluxus

import scala.language.postfixOps
import Implicits.*

case class PetCardProps(
    name: String,
    species: String,
    mood: String,
    onFeed: () => Unit,
)

val PetCard: PartialFunction[PetCardProps, FluxusNode] = {
  case PetCardProps(name, species, mood, onFeed) =>
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
}

case class HeaderProps(title: String)

val DashboardHeader: PartialFunction[HeaderProps, FluxusNode] = {
  case HeaderProps(title) =>
    div(
      cls := "text-center",
      h1(
        cls := "text-4xl font-bold text-primary",
        title,
      ),
      p(
        cls := "mt-2 text-base-content/70",
        "Keep track of your pets' moods and feeding schedule",
      ),
    )
}

def PetDashboard(props: Props): FluxusNode =
  val (lastFed, setLastFed) = useState("Never")

  div(
    cls := "min-h-screen bg-base-200 p-8",
    div(
      cls := "max-w-2xl mx-auto",
      DashboardHeader(
        HeaderProps(title = "Pet Status Dashboard"),
      ),
      div(
        cls := "grid grid-cols-1 md:grid-cols-2 gap-4 mt-6",
        PetCard(
          PetCardProps(
            name = "Whiskers",
            species = "Cat",
            mood = "Grumpy",
            onFeed = () => setLastFed("Just now"),
          ),
        ),
        PetCard(
          PetCardProps(
            name = "Rover",
            species = "Dog",
            mood = "Excited",
            onFeed = () => setLastFed("Just now"),
          ),
        ),
      ),
      div(
        cls := "mt-6 text-center text-sm text-base-content/60",
        s"Last pet fed: $lastFed",
      ),
    ),
  )

//@main def run(): Unit = renderApp("app", PetDashboard)
