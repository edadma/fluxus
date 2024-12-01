package io.github.edadma.fluxus.core.util

import scalajs.js.Date

object IdGenerator {
  private var counter: Long = 0

  // Get current time in milliseconds using JS Date
  private def now(): Long = Date.now().toLong

  // Component IDs: "c-" prefix for component
  def nextComponentId(): String = {
    counter += 1
    f"c-${now()}-$counter%08d"
  }

  // Element IDs: "e-" prefix for element
  def nextElementId(): String = {
    counter += 1
    f"e-${now()}-$counter%08d"
  }

  // List item IDs: "i-" prefix for item
  def nextListItemId(): String = {
    counter += 1
    f"i-${now()}-$counter%08d"
  }
}
