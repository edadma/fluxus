# useFetch Hook

`useFetch` is a custom hook in Fluxus that provides a convenient way to fetch data from APIs with built-in loading states, error handling, and JSON decoding.

## Basic Usage

```scala
val (state, retry) = useFetch[T](
  url = "https://api.example.com/data",
  dependencies = Seq(dependency1, dependency2),
  options = FetchOptions(/* configuration */)
)
```

Where:
- `T` is the expected type of the response data (must have a `JsonDecoder` instance)
- `state` is a `FetchState[T]` representing the current state of the fetch
- `retry` is a function that can be called to retry the fetch
- `dependencies` is an optional array of values that will trigger a refetch when changed
- `options` is an optional configuration object for the fetch request

## FetchState

The state of a fetch operation is represented by the `FetchState` enum:

```scala
enum FetchState[T]:
  case Success(data: T)
  case Error(error: FetchError)
  case Idle()
  case Loading()
```

## FetchError

Errors are represented by the `FetchError` enum:

```scala
enum FetchError extends Throwable:
  case NetworkError(message: String)
  case DecodeError(message: String)
  case HttpError(status: Int, statusText: String)
```

## FetchOptions

The `FetchOptions` case class allows you to configure the fetch request:

```scala
case class FetchOptions(
  method: String = "GET",
  headers: Map[String, String] = Map.empty,
  body: Option[String] = None,
  retries: Int = 3,
  retryDelay: Int = 1000,
  mode: String = "cors"
)
```

## Examples

### Basic Data Fetching

```scala
case class User(id: Int, name: String, email: String) derives JsonDecoder

val UserProfile = (props: UserProfileProps) => {
  val (state, retry) = useFetch[User](
    url = s"https://api.example.com/users/${props.userId}",
    dependencies = Seq(props.userId)
  )
  
  div(
    cls := "user-profile",
    state match {
      case FetchState.Loading() =>
        div(cls := "loading", "Loading user data...")
        
      case FetchState.Success(user) =>
        div(
          h1(user.name),
          p(user.email),
          button(
            onClick := (() => retry()),
            "Refresh"
          )
        )
        
      case FetchState.Error(error) =>
        div(
          cls := "error",
          p(s"Error: ${error.getMessage}"),
          button(
            onClick := (() => retry()),
            "Retry"
          )
        )
        
      case FetchState.Idle() =>
        div("No user data loaded yet")
    }
  )
}
```

### Fetching a List

```scala
case class TodoItem(id: Int, title: String, completed: Boolean) derives JsonDecoder

val TodoList = () => {
  val (state, retry) = useFetch[List[TodoItem]](
    url = "https://jsonplaceholder.typicode.com/todos",
    options = FetchOptions(
      headers = Map(
        "Accept" -> "application/json"
      )
    )
  )
  
  div(
    h1("Todo List"),
    state match {
      case FetchState.Loading() =>
        div(cls := "loading-spinner", "Loading todos...")
        
      case FetchState.Success(todos) =>
        div(
          p(s"${todos.size} todos loaded"),
          ul(
            cls := "todo-list",
            todos.take(10).map(todo => 
              li(
                key := todo.id.toString,
                input(
                  typ := "checkbox",
                  checked := todo.completed,
                  disabled := true
                ),
                span(todo.title)
              )
            )
          ),
          button(
            onClick := (() => retry()),
            "Refresh"
          )
        )
        
      case FetchState.Error(error) =>
        div(
          cls := "alert alert-error",
          p(s"Failed to load todos: ${error.getMessage}"),
          button(
            onClick := (() => retry()),
            "Retry"
          )
        )
        
      case FetchState.Idle() =>
        div("Click to load todos")
    }
  )
}
```

### POST Request with JSON Body

```scala
case class LoginRequest(username: String, password: String) derives JsonEncoder
case class LoginResponse(token: String, user: User) derives JsonDecoder

val LoginForm = () => {
  val (username, setUsername, _) = useState("")
  val (password, setPassword, _) = useState("")
  val (loginState, login) = useFetch[LoginResponse](
    url = "", // Empty URL initially, will be set when logging in
    dependencies = Seq()
  )
  
  def handleSubmit(e: dom.Event): Unit = {
    e.preventDefault()
    
    // Create a new fetch with the login data
    val loginData = LoginRequest(username, password)
    login(
      url = "https://api.example.com/login",
      options = FetchOptions(
        method = "POST",
        headers = Map(
          "Content-Type" -> "application/json"
        ),
        body = Some(loginData.toJson)
      )
    )
  }
  
  div(
    form(
      onSubmit := handleSubmit,
      div(
        label("Username"),
        input(
          typ := "text",
          value := username,
          onInput := ((e: dom.Event) => 
            setUsername(e.target.asInstanceOf[dom.html.Input].value)
          )
        )
      ),
      div(
        label("Password"),
        input(
          typ := "password",
          value := password,
          onInput := ((e: dom.Event) => 
            setPassword(e.target.asInstanceOf[dom.html.Input].value)
          )
        )
      ),
      loginState match {
        case FetchState.Loading() =>
          button(
            typ := "submit",
            disabled := true,
            "Logging in..."
          )
          
        case FetchState.Error(error) =>
          div(
            cls := "error",
            p(s"Login failed: ${error.getMessage}"),
            button(typ := "submit", "Try Again")
          )
          
        case _ =>
          button(typ := "submit", "Log In")
      }
    ),
    loginState match {
      case FetchState.Success(response) =>
        div(
          cls := "success",
          p(s"Welcome, ${response.user.name}!"),
          p(s"Your token: ${response.token}")
        )
      case _ => null
    }
  )
}
```

### Implementing Pagination

```scala
case class PaginatedResponse[T](
  items: List[T],
  totalItems: Int,
  page: Int,
  totalPages: Int
) derives JsonDecoder

case class Product(id: Int, name: String, price: Double) derives JsonDecoder

val ProductList = () => {
  val (page, setPage, _) = useState(1)
  val (state, retry) = useFetch[PaginatedResponse[Product]](
    url = s"https://api.example.com/products?page=$page&limit=10",
    dependencies = Seq(page)
  )
  
  div(
    h1("Products"),
    state match {
      case FetchState.Loading() =>
        div(cls := "loading-spinner", "Loading products...")
        
      case FetchState.Success(response) =>
        div(
          table(
            cls := "products-table",
            thead(
              tr(
                th("ID"),
                th("Name"),
                th("Price")
              )
            ),
            tbody(
              response.items.map(product => 
                tr(
                  key := product.id.toString,
                  td(product.id.toString),
                  td(product.name),
                  td(f"$${product.price}%.2f")
                )
              )
            )
          ),
          div(
            cls := "pagination",
            button(
              onClick := (() => setPage(page - 1)),
              disabled := (page <= 1),
              "Previous"
            ),
            span(s"Page $page of ${response.totalPages}"),
            button(
              onClick := (() => setPage(page + 1)),
              disabled := (page >= response.totalPages),
              "Next"
            )
          )
        )
        
      case FetchState.Error(error) =>
        div(
          cls := "error",
          p(s"Error: ${error.getMessage}"),
          button(
            onClick := (() => retry()),
            "Retry"
          )
        )
        
      case FetchState.Idle() =>
        div("No products loaded yet")
    }
  )
}
```

## Advanced Features

### Automatic Retries

`useFetch` automatically retries failed requests based on the `retries` and `retryDelay` options:

```scala
val (state, retry) = useFetch[User](
  url = "https://api.example.com/user/123",
  options = FetchOptions(
    retries = 5,     // Retry up to 5 times
    retryDelay = 500 // Wait 500ms between retries
  )
)
```

By default, it will retry network errors and server errors (5xx), but not client errors (4xx) or decode errors.

### Manual Retry

You can manually retry a fetch with the returned `retry` function:

```scala
button(
  onClick := (() => retry()),
  "Reload Data"
)
```

### Cancellation

`useFetch` automatically cancels in-flight requests when:
- The component unmounts
- The dependencies change, triggering a new fetch
- You manually trigger a new fetch

This prevents race conditions and memory leaks.

## TypeScript/Scala Integration

The `useFetch` hook is fully typed and integrates with Scala's type system. The type parameter `T` ensures that:

1. The JSON response is decoded to the correct Scala type
2. The `Success` case of `FetchState` contains a value of type `T`
3. The compiler can check that you're handling all possible states

For this to work, your data types need to have a `JsonDecoder` instance, which can be derived using the `derives` keyword with ZIO JSON:

```scala
case class Person(name: String, age: Int) derives JsonDecoder
```

## Implementation Details

Under the hood, `useFetch` uses a combination of:

- `useState` to track the fetch state
- `useEffect` to trigger the fetch and handle cleanup
- `js.Promise` for the actual network request
- ZIO JSON for decoding the response

The hook also handles common edge cases like:
- Race conditions between multiple in-flight requests
- Component unmounting during a fetch
- Network errors and timeout handling
- JSON decoding errors