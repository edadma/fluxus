package io.github.edadma.fluxus.examples

import io.github.edadma.fluxus.*
import scala.scalajs.js

// Create a JS facade for the raw JSON structure
@js.native
trait UserJS extends js.Object {
  val id: Int            = js.native
  val name: String       = js.native
  val username: String   = js.native
  val email: String      = js.native
  val website: String    = js.native
  val company: CompanyJS = js.native
}

@js.native
trait CompanyJS extends js.Object {
  val name: String        = js.native
  val catchPhrase: String = js.native
}

// Update case classes with conversion methods
case class User(
    id: Int,
    name: String,
    username: String,
    email: String,
    website: String,
    company: Company,
)

object User {
  def fromJS(obj: UserJS): User = User(
    id = obj.id,
    name = obj.name,
    username = obj.username,
    email = obj.email,
    website = obj.website,
    company = Company.fromJS(obj.company),
  )
}

case class Company(
    name: String,
    catchPhrase: String,
)

object Company {
  def fromJS(obj: CompanyJS): Company = Company(
    name = obj.name,
    catchPhrase = obj.catchPhrase,
  )
}

object JSONPlaceholderApp:
  def App: FluxusNode =
    div(
      cls := "min-h-screen bg-base-200 p-8",
      UsersTable <> (),
    )

  def UsersTable: () => FluxusNode = () => {
    // Use the useFetch hook to get users
    val (users, retry) = useFetch[js.Array[UserJS]](
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
                  userList.toSeq.map(jsUser => {
                    val user = User.fromJS(jsUser)
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
                    )
                  }),
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
