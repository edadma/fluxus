package io.edadma.fluxus.logging

import org.scalajs.dom.console

object Logger {
  enum LogLevel {
    case OFF, ERROR, WARN, INFO, DEBUG, TRACE
  }
  
  enum Category {
    case Component   // Component operations
    case VirtualDOM  // Virtual DOM operations
    case StateEffect // State & Effects
    case Memory     // Memory management  
    case Render     // Render cycle
  }

  private var currentLevel = LogLevel.INFO
  private var operationId = 0

  def nextOperationId: Int = {
    operationId += 1
    operationId
  }
  
  def setLevel(level: LogLevel): Unit = {
    currentLevel = level
  }
  
  def log(level: LogLevel, category: Category, message: String, opId: Int, data: Any*): Unit = {
    if (shouldLog(level)) {
      val ctx = s"[$level][${category}][op-$opId]"
      level match {
        case LogLevel.ERROR => console.error(ctx, message, data*)
        case LogLevel.WARN  => console.warn(ctx, message, data*)
        case _             => console.log(ctx, message, data*)
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
