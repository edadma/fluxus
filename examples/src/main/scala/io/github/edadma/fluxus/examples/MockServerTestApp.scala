package io.github.edadma.fluxus.examples

import io.github.edadma.fluxus.*
import zio.json.*

object MockServerTestApp:
  case class User(
      id: Int,
      name: String,
      email: String,
  ) derives JsonEncoder, JsonDecoder

  val mockServer = MockServer(
    MockEndpoint(
      path = "/api/users",
      handler =
        _ =>
          new MockResponse().json(
            List(
              User(1, "John Doe", "john@example.com"),
              User(2, "Jane Smith", "jane@example.com"),
            ),
          ),
    ),
    MockEndpoint(
      path = "/api/error",
      handler = _ => new MockResponse().status(500, "Internal Server Error"),
    ),
  )

  // Override fetch for this demo
  mockServer.overrideFetch()

  def App: FluxusNode =
    div(
      cls := "min-h-screen bg-base-200 p-8 flex justify-center items-center",
      MockServerDemo <> (),
    )

  def MockServerDemo: () => FluxusNode = () => {
    // Use state to manage different scenarios
    val (scenario, setScenario, _) = useState("idle")

    // Fetch hook for users
    val (userState, userRetry) = useFetch[List[User]](
      url = scenario match {
        case "success" => "/api/users"
        case "error"   => "/api/error"
        case _         => ""
      },
      dependencies = Seq(scenario),
    )

    div(
      cls := "card w-96 bg-base-100 shadow-xl",
      div(
        cls := "card-body",
        h2(cls := "card-title", "Mock Server Demo"),

        // Scenario selection buttons
        div(
          cls := "btn-group mb-4",
          button(
            cls     := s"btn ${if scenario == "idle" then "btn-outline" else ""}",
            onClick := (() => setScenario("idle")),
            "Idle",
          ),
          button(
            cls     := s"btn ${if scenario == "success" then "btn-outline" else ""}",
            onClick := (() => setScenario("success")),
            "Fetch Users",
          ),
          button(
            cls     := s"btn ${if scenario == "error" then "btn-outline" else ""}",
            onClick := (() => setScenario("error")),
            "Trigger Error",
          ),
        ),

        // Result display
        div(
          cls := "mt-4",
          userState match {
            case FetchState.Idle() =>
              div(
                cls := "alert alert-info",
                "Select a scenario to test mock server",
              )

            case FetchState.Loading() =>
              div(
                cls := "loading loading-spinner loading-lg",
                "Loading...",
              )

            case FetchState.Success(users) =>
              div(
                cls := "overflow-x-auto",
                table(
                  cls := "table table-zebra",
                  thead(
                    tr(
                      th("ID"),
                      th("Name"),
                      th("Email"),
                    ),
                  ),
                  tbody(
                    users.map(user =>
                      tr(
                        key := user.id.toString,
                        td(user.id.toString),
                        td(user.name),
                        td(user.email),
                      ),
                    ),
                  ),
                ),
              )

            case FetchState.Error(error) =>
              div(
                cls := "alert alert-error",
                span(error.getMessage),
                button(
                  cls     := "btn btn-sm btn-outline ml-2",
                  onClick := (() => userRetry()),
                  "Retry",
                ),
              )
          },
        ),
      ),
    )
  }
