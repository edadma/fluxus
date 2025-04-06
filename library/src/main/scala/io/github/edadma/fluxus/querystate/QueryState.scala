package io.github.edadma.fluxus.querystate

import com.raquo.airstream.core.Transaction
import com.raquo.airstream.state.Var
import io.github.edadma.fluxus.*
import org.scalajs.dom
import org.scalajs.dom.window

import scala.scalajs.js.URIUtils

/** A utility for synchronizing application state with URL query parameters. This allows for shareable URLs and browser
  * history integration.
  */
object QueryState {
  // Map of parameter signals
  private val paramSignals = collection.mutable.Map[String, Var[String]]()

  // Config options
  private var useHashMode = false
  private var initialized = false

  /** Initialize the query state manager.
    * @param defaults
    *   Default values for parameters
    * @param useHash
    *   Whether to use hash-based URL parameters (#param=value)
    */
  def init(defaults: Map[String, String] = Map(), useHash: Boolean = false): Unit = {
    useHashMode = useHash

    // Read initial values from URL
    val urlParams = parseQueryParams()

    // Initialize signals with URL values or defaults
    Transaction { _ =>
      defaults.foreach { case (key, defaultValue) =>
        val initialValue = urlParams.getOrElse(key, defaultValue)
        paramSignals.getOrElseUpdate(key, Var(initialValue)).set(initialValue)
      }
    }

    // Set up browser navigation event listener
    setupHistoryListener()
    initialized = true
  }

  /** Get or create a parameter signal with an optional default value.
    * @param key
    *   Parameter name
    * @param defaultValue
    *   Default value if parameter doesn't exist
    * @return
    *   Signal for the parameter
    */
  def param(key: String, defaultValue: String = ""): Var[String] = {
    if (!initialized) {
      init()
    }
    paramSignals.getOrElseUpdate(key, Var(defaultValue))
  }

  /** Update a single parameter value.
    * @param key
    *   Parameter name
    * @param value
    *   New parameter value
    */
  def setParam(key: String, value: String): Unit = {
    if (!initialized) {
      init()
    }

    Transaction { _ =>
      paramSignals.getOrElseUpdate(key, Var(value)).set(value)
    }
    updateQueryParams()
  }

  /** Update multiple parameters at once.
    * @param params
    *   Map of parameter names to values
    */
  def updateParams(params: Map[String, String]): Unit = {
    if (!initialized) {
      init()
    }

    Transaction { _ =>
      params.foreach { case (key, value) =>
        paramSignals.getOrElseUpdate(key, Var(value)).set(value)
      }
    }
    updateQueryParams()
  }

  /** Reset all parameters to their defaults or empty strings.
    * @param defaults
    *   Map of default values to reset to
    */
  def reset(defaults: Map[String, String] = Map()): Unit = {
    if (!initialized) {
      init(defaults)
      return
    }

    Transaction { _ =>
      paramSignals.keys.foreach { key =>
        paramSignals(key).set(defaults.getOrElse(key, ""))
      }
    }
    updateQueryParams()
  }

  /** Get current parameters as a Map.
    * @return
    *   Map of current parameter values
    */
  def currentParams: Map[String, String] = {
    if (!initialized) {
      init()
    }

    paramSignals.map { case (key, signal) => key -> signal.now() }.toMap
  }

  /** Generate a URL with the given parameters.
    * @param params
    *   Map of parameter names to values
    * @return
    *   URL string with the parameters
    */
  def urlFor(params: Map[String, String]): String = {
    val queryString = params.filter(_._2.nonEmpty).map { case (key, value) =>
      s"$key=${URIUtils.encodeURIComponent(value)}"
    }.mkString("&")

    if (useHashMode) {
      if (queryString.isEmpty) window.location.pathname else s"${window.location.pathname}#$queryString"
    } else {
      if (queryString.isEmpty) window.location.pathname else s"${window.location.pathname}?$queryString"
    }
  }

  // Private methods for URL handling
  private def parseQueryParams(): Map[String, String] = {
    val queryString = if (useHashMode) {
      window.location.hash.stripPrefix("#")
    } else {
      window.location.search.stripPrefix("?")
    }

    if (queryString.isEmpty) Map.empty
    else {
      queryString.split("&").map { param =>
        val parts = param.split("=")
        if (parts.length > 1) parts(0) -> URIUtils.decodeURIComponent(parts(1))
        else parts(0)                  -> ""
      }.toMap
    }
  }

  private def updateQueryParams(): Unit = {
    val params = paramSignals.map { case (key, signal) => key -> signal.now() }.toMap.filter(_._2.nonEmpty)
    val url    = urlFor(params)

    // Update browser URL
    window.history.pushState(null, "", url)
  }

  private def setupHistoryListener(): Unit = {
    // Handle browser navigation events (back/forward buttons)
    val handler = (_: dom.Event) => {
      val params = parseQueryParams()

      // Update signals with new URL values, but don't trigger another URL update
      Transaction { _ =>
        paramSignals.foreach { case (key, signal) =>
          signal.set(params.getOrElse(key, signal.now()))
        }
      }
    }

    window.addEventListener("popstate", handler)
  }
}

/** Hook to use a query parameter in a component. Returns a tuple of (current value, setter function, updater function)
  * similar to Fluxus's useState hook.
  *
  * @param key
  *   Parameter name
  * @param defaultValue
  *   Default value if parameter doesn't exist
  * @return
  *   Tuple of (current value, setter function, updater function)
  */
def useQueryParam(key: String, defaultValue: String = ""): (String, String => Unit, (String => String) => Unit) = {
  val signal = QueryState.param(key, defaultValue)
  val value  = useSignal(signal)

  // Direct setter function
  val setValue = (newValue: String) => {
    QueryState.setParam(key, newValue)
  }

  // Functional updater that receives current value and returns new value
  val updateValue = (updateFn: String => String) => {
    val currentValue = signal.now()
    val newValue     = updateFn(currentValue)
    QueryState.setParam(key, newValue)
  }

  (value, setValue, updateValue)
}
