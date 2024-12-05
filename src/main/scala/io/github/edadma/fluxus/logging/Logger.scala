package io.github.edadma.fluxus.logging

import org.scalajs.dom.console

object Logger {
  enum LogLevel {
    case OFF, ERROR, WARN, INFO, DEBUG, TRACE
  }

  enum Category {
    case Component   // Component operations
    case VirtualDOM  // Virtual DOM operations
    case StateEffect // State & Effects
    case Memory      // Memory management
    case Render      // Render cycle
  }

  private var currentLevel = LogLevel.INFO
  private var operationId  = 0

  def resetOperationId(): Unit = {
    operationId = 0
  }

  def nextOperationId: Int = {
    operationId += 1
    operationId
  }

  def setLevel(level: LogLevel): Unit = {
    currentLevel = level
  }

  private def formatData(data: Any): String = data match {
    case m: Map[_, _] =>
      m.iterator
        .map { case (k, v) => s"$k: ${formatData(v)}" }
        .mkString("{ ", ", ", " }")

    case s: Seq[_] =>
      s.map(formatData).mkString("[", ", ", "]")

    case p: Product if p.productPrefix.startsWith("sci_") =>
      if (p.productPrefix.contains("Map")) {
        p.asInstanceOf[Map[?, ?]]
          .iterator
          .map { case (k, v) => s"$k: ${formatData(v)}" }
          .mkString("{ ", ", ", " }")
      } else p.toString

    case x => x.toString
  }

  def log(level: LogLevel, category: Category, message: String, opId: Int, data: Any*): Unit = {
    if (shouldLog(level)) {
      val ctx           = s"[$level][${category}][op-$opId]"
      val formattedData = data.map(formatData)
      val logMessage    = (ctx +: message +: formattedData).mkString(" ")

      level match {
        case LogLevel.ERROR => console.error(logMessage)
        case LogLevel.WARN  => console.warn(logMessage)
        case _              => console.log(logMessage)
      }
    }
  }

  def error(category: Category, message: String, opId: Int, data: Any*): Unit =
    log(LogLevel.ERROR, category, message, opId, data*)

  def warn(category: Category, message: String, opId: Int, data: Any*): Unit =
    log(LogLevel.WARN, category, message, opId, data*)

  def info(category: Category, message: String, opId: Int, data: Any*): Unit =
    log(LogLevel.INFO, category, message, opId, data*)

  def debug(category: Category, message: String, opId: Int, data: Any*): Unit =
    log(LogLevel.DEBUG, category, message, opId, data*)

  def trace(category: Category, message: String, opId: Int, data: Any*): Unit =
    log(LogLevel.TRACE, category, message, opId, data*)

  private def shouldLog(level: LogLevel): Boolean =
    level.ordinal <= currentLevel.ordinal
}
