package io.github.edadma.fluxus.examples

import io.github.edadma.fluxus.*
import scala.scalajs.js

// Define User case class to match API structure
case class User(
    id: Int,
    name: String,
    username: String,
    email: String,
    website: String,
    company: Company,
)

// Nested case class for company details
case class Company(
    name: String,
    catchPhrase: String,
)

object JSONPlaceholderApp:
  def App: FluxusNode =
    div(
      cls := "min-h-screen bg-base-200 p-8",
      UsersTable <> (),
    )

  def UsersTable: () => FluxusNode = () => {
    // Use the useFetch hook to get users
    val (users, retry) = useFetch[js.Array[User]](
      url = "https://cors-anywhere.herokuapp.com/https://jsonplaceholder.typicode.com/users",
      options = FetchOptions(
        retries = 1,
        retryDelay = 500,
      ),
    )

    div(
      cls := "card bg-base-100 shadow-xl",
      div(
        cls := "card-body",
        h2(
          cls := "card-title text-2xl mb-4",
          "JSONPlaceholder Users",
        ),
        // Render different views based on fetch state
        users match {
          case FetchState.Idle() =>
            div(
              cls := "alert alert-info",
              "Ready to fetch users",
            )

          case FetchState.Loading() =>
            div(
              cls := "flex justify-center items-center",
              span(
                cls := "loading loading-spinner loading-lg text-primary",
              ),
            )

          case FetchState.Success(userList) =>
            div(
              cls := "overflow-x-auto",
              table(
                cls := "table table-zebra table-pin-rows",
                thead(
                  tr(
                    th("ID"),
                    th("Name"),
                    th("Username"),
                    th("Email"),
                    th("Company"),
                    th("Website"),
                  ),
                ),
                tbody(
                  userList.map(user =>
                    tr(
                      td(user.id.toString),
                      td(user.name),
                      td(user.username),
                      td(user.email),
                      td(user.company.name),
                      td(
                        a(
                          href   := s"https://${user.website}",
                          target := "_blank",
                          cls    := "link link-primary",
                          user.website,
                        ),
                      ),
                    ),
                  ),
                ),
              ),
            )

          case FetchState.Error(message) =>
            div(
              cls := "alert alert-error",
              span(s"Failed to load users: $message"),
              button(
                cls     := "btn btn-sm btn-outline btn-error ml-4",
                onClick := (() => retry()),
                "Retry",
              ),
            )
        },
      ),
    )
  }
