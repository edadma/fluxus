package io.github.edadma.fluxus

import org.scalajs.dom
import zio.json.*
import scala.concurrent.Future
import scala.scalajs.js
import scala.scalajs.js.JSConverters.*
import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
import scala.util.{Success, Failure}

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
    retryDelay: Int = 1000,
    mode: String = "cors",
)

def useFetch[T: JsonDecoder](
    url: String,
    dependencies: Seq[Any] = Seq.empty,
    options: FetchOptions = FetchOptions(),
): (FetchState[T], () => Unit) = {
  // State for managing fetch results
  val (state, setState, _) = useState[FetchState[T]](FetchState.Idle())

  // Cancellation flag to prevent updates on unmounted components
  var isCancelled = false

  // Function to perform fetch with retry logic
  def performFetch(remainingRetries: Int): Unit = {
    // Set loading state
    setState(FetchState.Loading())

    val init = js.Dynamic.literal(
      method = options.method,
      headers = options.headers.toJSDictionary,
      mode = options.mode,
    )

    options.body.foreach(init.updateDynamic("body")(_))

    // Perform fetch with error handling
    dom.fetch(url, init.asInstanceOf[dom.RequestInit]).toFuture
      .flatMap(response =>
        if (!response.ok)
          Future.failed(new Error(s"HTTP error! status: ${response.status}"))
        else
          response.text().toFuture,
      )
      .map(_.fromJson[T])
      .onComplete {
        case Success(Right(data)) if !isCancelled => setState(FetchState.Success(data))
        case Success(Left(error)) if !isCancelled => setState(FetchState.Error(s"JSON decode error: $error"))
        case Failure(error) if !isCancelled =>
          if (remainingRetries > 0) {
            js.timers.setTimeout(options.retryDelay) {
              performFetch(remainingRetries - 1)
            }
          } else {
            setState(FetchState.Error(error.getMessage))
          }
        case _ => // Request was cancelled
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
      }

      // Cleanup function
      () => {
        isCancelled = true
      }
    },
    url +: dependencies,
  ) // Trigger fetch on URL or dependency changes

  // Return both the state and a retry function
  (state, retry)
}
