package io.github.edadma.fluxus.examples

import io.github.edadma.fluxus.*
import scala.scalajs.js
import zio.json.*

case class User(
    id: Int,
    name: String,
    username: String,
    email: String,
    website: String,
    company: Company,
) derives JsonDecoder

case class Company(
    name: String,
    catchPhrase: String,
) derives JsonDecoder

object JSONPlaceholderApp:
  def App: FluxusNode =
    div(
      cls := "min-h-screen bg-base-200 p-8",
      UsersTable <> (),
    )

  def UsersTable: () => FluxusNode = () => {
    // Use the useFetch hook to get users
    val (users, retry) = useFetch[List[User]](
      url = "https://jsonplaceholder.typicode.com/users",
      options = FetchOptions(
        headers = Map(
          "Accept"       -> "application/json",
          "Content-Type" -> "application/json",
        ),
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
                  // Show 5 skeleton rows while loading
                  (1 to 5).map(id =>
                    tr(
                      key := s"s-$id",
                      td(div(cls := "skeleton h-4 w-8")), // ID - narrow
                      td(div(cls := "skeleton h-4 w-32")), // Name - medium
                      td(div(cls := "skeleton h-4 w-24")), // Username - medium
                      td(div(cls := "skeleton h-4 w-40")), // Email - wide
                      td(div(cls := "skeleton h-4 w-32")), // Company - medium
                      td(div(cls := "skeleton h-4 w-24")), // Website - medium
                    ),
                  ),
                ),
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
                  // Convert JS objects to Scala case classes
                  userList.map(user =>
                    tr(
                      key := user.id,
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
