package io.github.edadma.fluxus.core

import io.github.edadma.fluxus.{StateHook, logger}
import org.scalajs.dom

import scala.collection.mutable
import scala.scalajs.js

/** Handles batching of state updates to prevent unnecessary re-renders and ensure consistent state updates.
  */
object BatchScheduler {
  // Tracks if we're currently processing a batch
  private var isProcessing = false

  // Queue of pending updates
  private val updates = mutable.Queue[StateUpdate]()

  /** Represents a single state update operation
    * @param instance
    *   The component instance being updated
    * @param hook
    *   The state hook being updated
    * @param updateFn
    *   Function to compute the new state value
    */
  case class StateUpdate(
      instance: ComponentInstance,
      hook: StateHook[?],
      updateFn: Any => Any,
  )

  /** Schedules a direct state update
    */
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

  /** Schedules a functional state update
    */
  def scheduleFunctionalUpdate[T](
      instance: ComponentInstance,
      hook: StateHook[T],
      fn: T => T,
  ): Unit = {
    logger.debug(
      "Scheduling functional update",
      category = "BatchScheduler",
      Map(
        "instance" -> instance.id,
      ),
    )

    updates.enqueue(StateUpdate(instance, hook, fn.asInstanceOf[Any => Any]))
    scheduleBatchProcessing()
  }

  /** Schedules the processing of the current batch if not already processing
    */
  private def scheduleBatchProcessing(): Unit = {
    if (!isProcessing) {
      isProcessing = true

      // Use Promise to schedule in next microtask
      js.Promise.resolve(()).`then`(_ => processBatch())
    }
  }

  /** Processes all updates in the current batch
    */
  private def processBatch(): Unit = {
    logger.debug(
      "Processing batch - start",
      category = "BatchScheduler",
      Map(
        "queueSize" -> updates.size.toString,
        "updates"   -> updates.map(u => s"${u.instance.id}: ${u.hook.value}").mkString(", "),
      ),
    )

    // Get all updates in current batch
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

    // Render each affected component once
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

    // If more updates came in during processing, process them
    if (updates.nonEmpty) {
      processBatch()
    }
  }
}
