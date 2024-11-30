package io.github.edadma.fluxus

import scala.scalajs.js

object FluxusLogger:
  private var enabled       = true
  private val startTime     = js.Date.now()
  private var lastEventTime = startTime

  // Log levels
  enum Level:
    case Debug, Info, Warn, Error

  // Enable/disable all logging
  def setEnabled(isEnabled: Boolean): Unit = enabled = isEnabled

  // Core logging function with timestamps and categories
  private def log(level: Level, category: String, message: String, data: Map[String, Any] = Map()): Unit =
    if enabled then
      val now            = js.Date.now()
      val timeSinceStart = (now - startTime).toInt
      val timeSinceLast  = (now - lastEventTime).toInt
      lastEventTime = now

      val dataStr = if data.nonEmpty then
        data.map { case (k, v) => s"$k: $v" }.mkString(", ")
      else ""

      println(f"[$timeSinceStart%4dms Δ$timeSinceLast%4dms] ${level.toString.toUpperCase}-$category: $message $dataStr")

  // Public logging interfaces
  object Render:
    def component(name: String): Unit =
      log(Level.Debug, "Render", s"Component: $name")

    def domUpdate(operation: String, details: String): Unit =
      log(Level.Debug, "DOM", s"$operation - $details")

    def stats(renderCount: Int, details: Map[String, Any]): Unit =
      log(Level.Info, "Stats", s"Render #$renderCount", details)

  object Events:
    def cleanup(component: String, count: Int): Unit =
      log(Level.Debug, "Events", s"Cleanup for $component", Map("removed" -> count))

    def add(component: String, eventType: String): Unit =
      log(Level.Debug, "Events", s"Add to $component", Map("type" -> eventType))

    def debug(message: String, data: Map[String, Any] = Map()): Unit =
      log(Level.Debug, "Events", message, data)

    def warn(message: String, data: Map[String, Any] = Map()): Unit =
      log(Level.Warn, "Events", message, data)

  object State:
    def update(component: String, value: Any): Unit =
      log(Level.Debug, "State", s"Update in $component", Map("value" -> value))

    def effect(component: String, description: String): Unit =
      log(Level.Debug, "Effect", s"Run in $component: $description")

  object Props:
    def debug(message: String, data: Map[String, Any] = Map()): Unit =
      log(Level.Debug, "Props", message, data)

    def trace(message: String, data: Map[String, Any] = Map()): Unit =
      log(Level.Debug, "Props-Trace", message, data)

  object Memory:
    def report(): Unit =
      val mem = js.Dynamic.global.performance.memory.asInstanceOf[js.Dynamic]
      log(
        Level.Info,
        "Memory",
        "Heap snapshot",
        Map(
          "used"  -> f"${mem.usedJSHeapSize.asInstanceOf[Double] / 1024 / 1024}%.2f MB",
          "total" -> f"${mem.totalJSHeapSize.asInstanceOf[Double] / 1024 / 1024}%.2f MB",
          "limit" -> f"${mem.jsHeapSizeLimit.asInstanceOf[Double] / 1024 / 1024}%.2f MB",
        ),
      )
