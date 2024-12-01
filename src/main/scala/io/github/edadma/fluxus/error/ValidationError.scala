package io.github.edadma.fluxus.error

import io.github.edadma.fluxus.logging.Logger
import io.github.edadma.fluxus.logging.Logger.Category

sealed trait ValidationError extends Exception {
  val context: Map[String, Any]
  val message: String
  val opId: Int
}

case class NodeValidationError(
    message: String,
    context: Map[String, Any],
    opId: Int,
) extends ValidationError {
  Logger.error(Category.VirtualDOM, s"Node validation error: $message", opId, context)
}

case class PropValidationError(
    message: String,
    context: Map[String, Any],
    opId: Int,
) extends ValidationError {
  Logger.error(Category.Component, s"Prop validation error: $message", opId, context)
}

case class HookValidationError(
    message: String,
    context: Map[String, Any],
    opId: Int,
) extends ValidationError {
  Logger.error(Category.StateEffect, s"Hook validation error: $message", opId, context)
}

case class ResourceValidationError(
    message: String,
    context: Map[String, Any],
    opId: Int,
) extends ValidationError {
  Logger.error(Category.Memory, s"Resource validation error: $message", opId, context)
}
