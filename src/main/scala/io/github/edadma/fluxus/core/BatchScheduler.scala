package io.github.edadma.fluxus.core

import io.github.edadma.fluxus.{StateHook, logger}

import scala.collection.mutable

object BatchScheduler {
  private var isProcessing = false
  private val updates      = mutable.Queue[StateUpdate]()

  case class StateUpdate(
      instance: ComponentInstance,
      hook: StateHook[_],
      updateFn: Any => Any,
  )

  def scheduleUpdate[T](
      instance: ComponentInstance,
      hook: StateHook[T],
      value: T,
  ): Unit = {
    logger.debug(
      "Scheduling update",
      category = "BatchScheduler",
      Map(
        "instance" -> instance.id,
        "value"    -> value.toString,
      ),
    )

    updates.enqueue(StateUpdate(instance, hook, _ => value))
    scheduleBatchProcessing()
  }

  private def scheduleBatchProcessing(): Unit = {
    if (!isProcessing) {
      isProcessing = true
      processBatch()
    }
  }
}
