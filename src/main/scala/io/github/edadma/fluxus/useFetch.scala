package io.github.edadma.fluxus

import org.scalajs.dom
import org.scalajs.dom.RequestInit

import scala.scalajs.js
import scala.scalajs.js.JSConverters.*

def createRequestInit(
    method: String = "GET",
    headers: Map[String, String] = Map(
      "Content-Type" -> "application/json",
    ),
    body: Option[String] = None,
): dom.RequestInit = {
  val init = js.Dynamic.literal(
    method = method,
    headers = headers.toJSDictionary,
  )

  body.foreach(init.updateDynamic("body")(_))
  init.asInstanceOf[dom.RequestInit]
}

// Enum for fetch states
enum FetchState[T]:
  case Success(data: T)
  case Error(message: String)
  case Idle()
  case Loading()

case class FetchOptions(
    method: String = "GET",
    headers: Map[String, String] = Map.empty,
    body: Option[String] = None,
    retries: Int = 3,
    retryDelay: Int = 1000, // milliseconds
)

def useFetch[T](
    url: String,
    dependencies: Seq[Any] = Seq.empty,
    options: FetchOptions = FetchOptions(),
): (FetchState[T], () => Unit) = {
  // State for managing fetch results
  val (state, setState, _) = useState[FetchState[T]](FetchState.Idle())

  // Function to perform fetch with retry logic
  def performFetch(remainingRetries: Int): Unit = {
    // Set loading state
    setState(FetchState.Loading())

    // Cancellation flag to prevent updates on unmounted components
    var isCancelled = false

    val init = js.Dynamic.literal(
      method = method,
      headers = options.headers.toJSDictionary,
    )

    options.body.foreach(init.updateDynamic("body")(_))

    // Perform fetch with error handling
    dom.fetch(url, init.asInstanceOf[dom.RequestInit])
      .`then`(response =>
        if (!response.ok)
          throw new Error(s"HTTP error! status: ${response.status}")
        else
          response.json(),
      )
      .`then`(data =>
        if (!isCancelled) {
          setState(FetchState.Success(data.asInstanceOf[T]))
        },
      )
      .`catch`(error =>
        if (!isCancelled) {
          if (remainingRetries > 0) {
            // Schedule retry with exponential backoff
            js.timers.setTimeout(options.retryDelay) {
              performFetch(remainingRetries - 1)
            }
          } else {
            setState(FetchState.Error(error.toString))
          }
        },
      )

    // Cleanup function
    () => {
      isCancelled = true
    }
  }

  // Retry function exposed to the user
  def retry(): Unit = {
    performFetch(options.retries)
  }

  // Self-triggering effect
  useEffect(
    () => {
      // Only fetch if URL is non-empty
      if (url.nonEmpty) {
        performFetch(options.retries)
      } else () // No-op if URL is empty
    },
    url +: dependencies,
  ) // Trigger fetch on URL or dependency changes

  // Return both the state and a retry function
  (state, retry)
}
