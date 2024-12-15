package io.github.edadma.fluxus.core

import io.github.edadma.fluxus.{StateHook, logger}

import scala.collection.mutable

object BatchScheduler {
  private var isProcessing = false
  private val updates      = mutable.Queue[StateUpdate]()

  case class StateUpdate(
      instance: ComponentInstance,
      hook: StateHook[?],
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

  private def processBatch(): Unit = {
    logger.debug(
      "Processing batch - start",
      category = "BatchScheduler",
      Map(
        "queueSize" -> updates.size.toString,
        "updates"   -> updates.map(u => s"${u.instance.id}: ${u.hook.value}").mkString(", "),
      ),
    )

    // Process all updates in current batch
    val batch = updates.toSeq
    updates.clear()

    // Apply all state updates
    batch.foreach { update =>
      val oldValue = update.hook.value
      val newValue = update.updateFn(oldValue)

      logger.debug(
        "Applying update",
        category = "BatchScheduler",
        Map(
          "instance" -> update.instance.id,
          "oldValue" -> oldValue.toString,
          "newValue" -> newValue.toString,
        ),
      )

      update.hook.asInstanceOf[StateHook[Any]].value = newValue
    }

    // Get unique set of components that need re-rendering
    val componentsToUpdate = batch.map(_.instance).toSet

    logger.debug(
      "Processing batch - components to update",
      category = "BatchScheduler",
      Map(
        "components" -> componentsToUpdate.map(_.id).mkString(", "),
      ),
    )

    // Render each component once
    componentsToUpdate.foreach { instance =>
      logger.debug(
        "Re-rendering component",
        category = "BatchScheduler",
        Map(
          "instance" -> instance.id,
          "hooks"    -> instance.hooks.map(_.toString).mkString(", "),
        ),
      )

      instance.rerender()
    }

    isProcessing = false

    // Handle any updates that came in during processing
    if (updates.nonEmpty) {
      processBatch()
    }
  }
}
