package io.github.edadma.fluxus

import org.scalajs.dom
import zio.json.*
import scala.concurrent.Future
import scala.scalajs.js
import scala.scalajs.js.JSConverters.*
import org.scalajs.macrotaskexecutor.MacrotaskExecutor.Implicits.global
import scala.util.{Success, Failure}

enum FetchError extends Throwable:
  case NetworkError(message: String)              extends FetchError
  case DecodeError(message: String)               extends FetchError
  case HttpError(status: Int, statusText: String) extends FetchError

  // Override getMessage for better error reporting
  override def getMessage: String = this match
    case NetworkError(msg)       => s"Network error: $msg"
    case DecodeError(msg)        => s"Failed to decode response: $msg"
    case HttpError(status, text) => s"HTTP error $status: $text"

// Enum for fetch states
enum FetchState[T]:
  case Success(data: T)
  case Error(error: FetchError)
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

private def shouldRetry(error: FetchError): Boolean = error match
  case FetchError.NetworkError(_)      => true          // Always retry network errors
  case FetchError.HttpError(status, _) => status >= 500 // Only retry server errors
  case FetchError.DecodeError(_)       => false         // Don't retry decode errors

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

    dom.fetch(url, init.asInstanceOf[dom.RequestInit])
      .toFuture
      .flatMap { response =>
        if (!response.ok) {
          // Handle HTTP errors
          Future.failed(FetchError.HttpError(response.status, response.statusText))
        } else {
          response.text().toFuture
            .flatMap { jsonText =>
              jsonText.fromJson[T] match {
                case Left(error)  => Future.failed(FetchError.DecodeError(error))
                case Right(value) => Future.successful(value)
              }
            }
        }
      }
      .recoverWith { case e: Throwable =>
        e match {
          case e: FetchError => Future.failed(e)
          case _             => Future.failed(FetchError.NetworkError(e.getMessage))
        }
      }
      .onComplete {
        case Success(data) if !isCancelled =>
          setState(FetchState.Success(data))
        case Failure(error: FetchError) if !isCancelled =>
          if (remainingRetries > 0 && shouldRetry(error)) {
            js.timers.setTimeout(options.retryDelay) {
              performFetch(remainingRetries - 1)
            }
          } else {
            setState(FetchState.Error(error))
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
