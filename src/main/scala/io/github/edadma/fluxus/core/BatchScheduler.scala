package io.github.edadma.fluxus.core

import io.github.edadma.fluxus.{EffectHook, StateHook, logger}
import org.scalajs.dom

import scala.annotation.tailrec
import scala.collection.mutable
import scala.scalajs.js

/** Handles batching of state updates to prevent unnecessary re-renders and ensure consistent state updates.
  */
object BatchScheduler {
  // Tracks if we're currently processing a batch
//  private var isProcessing = false
  private class BatchState {
    var isProcessing = false
  }

  private val state = new BatchState()

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
        "instance id" -> instance.id,
        "instance"    -> instance.toString,
        "hook"        -> hook.toString,
        "value"       -> value.toString,
      ),
    )

    updates.enqueue(StateUpdate(instance, hook, _ => value))

    logger.debug(
      "Update enqueued",
      category = "BatchScheduler",
      Map(
        "queueSize" -> updates.size.toString,
      ),
    )

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
    logger.debug(
      "Schedule batch processing",
      category = "BatchScheduler",
      Map(
        "isProcessing" -> state.isProcessing.toString,
        "queueSize"    -> updates.size.toString,
      ),
    )

//    if (!isProcessing) {
//      logger.debug(
//        "Starting batch processing",
//        category = "BatchScheduler",
//        Map(
//          "updates" -> updates.map(u => s"${u.instance.id}: ${u.hook.value}").mkString(", "),
//        ),
//      )
//
//      isProcessing = true
//
//      // Use Promise to schedule in next microtask
//      js.Promise.resolve(()).`then`(_ => {
//        logger.debug(
//          "Promise resolved - about to process batch",
//          category = "BatchScheduler",
//        )
//
//        processBatch()
//      })
//    }

//    if (!isProcessing) {
//      isProcessing = true
//
//      js.timers.setTimeout(0) {
//        try {
//          logger.debug(
//            "Processing batch in promise",
//            category = "BatchScheduler",
//            Map(
//              "updates"   -> updates.map(u => s"${u.instance.id}: ${u.hook.value}").mkString(", "),
//              "queueSize" -> updates.size.toString,
//            ),
//          )
//          processBatch()
//        } finally {
//          // Always reset isProcessing so subsequent updates can be processed
//          isProcessing = false
//          // Check if more updates came in while we were processing
//          if (updates.nonEmpty) {
//            scheduleBatchProcessing()
//          }
//        }
//      }
//    } else {
//      // If already processing, ensure we have a new batch scheduled after current one finishes
//      logger.debug(
//        "Already processing, will schedule new batch after current",
//        category = "BatchScheduler",
//        Map("queueSize" -> updates.size.toString),
//      )
//    }

    if (!state.isProcessing) {
      state.isProcessing = true

      logger.debug(
        "Setting up timer",
        category = "BatchScheduler",
      )

      js.timers.setTimeout(0) {
        logger.debug(
          "Timer callback starting",
          category = "BatchScheduler",
          Map("queueSize" -> updates.size.toString),
        )

        try {
          val currentBatch = updates.toSeq // Get current updates
          updates.clear() // Clear queue

          processBatch(currentBatch) // Process only current batch
        } catch {
          case e: Throwable =>
            logger.error(
              "Error in batch processing",
              category = "BatchScheduler",
              Map("error" -> e.toString),
            )
            throw e
        } finally {
          logger.debug(
            "Timer callback ending",
            category = "BatchScheduler",
            Map(
              "isProcessing" -> state.isProcessing.toString,
              "queueSize"    -> updates.size.toString,
            ),
          )
          state.isProcessing = false
          if (updates.nonEmpty) {
            scheduleBatchProcessing()
          }
        }
      }
    } else {
      logger.debug(
        "Already processing, will schedule new batch after current",
        category = "BatchScheduler",
        Map("queueSize" -> updates.size.toString),
      )
    }

  }

  /** Processes all updates in the current batch
    */
  private def processBatch(batch: Seq[StateUpdate]): Unit = {
    logger.debug(
      "Processing batch - start",
      category = "BatchScheduler",
      Map(
        "queueSize" -> updates.size.toString,
        "updates"   -> updates.map(u => s"${u.instance.id}: ${u.hook.value}").mkString(", "),
      ),
    )

    logger.debug(
      "Processing batch - got batch",
      category = "BatchScheduler",
      Map(
        "batchSize" -> batch.size.toString,
        "instances" -> batch.map(u => s"instance: ${u.instance}").mkString(", "),
        "hooks"     -> batch.map(u => s"hook: ${u.hook}").mkString(", "),
        "fns"       -> batch.map(u => s"fn: ${u.updateFn}").mkString(", "),
      ),
    )

    // Apply all state updates
    batch.foreach { update =>
      logger.debug(
        "Processing update - before access",
        category = "BatchScheduler",
        Map(
          "instance" -> update.instance.toString,
          "hook"     -> update.hook.toString,
          "fn"       -> update.updateFn.toString,
        ),
      )

      val oldValue = update.hook.value
      val newValue = update.updateFn(oldValue)

      logger.debug(
        "After value update",
        category = "BatchScheduler",
        Map(
          "hook"     -> update.hook.toString,
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

    // Run effects for updated components
    componentsToUpdate.foreach { instance =>
      instance.hooks.foreach {
        case hook: EffectHook =>
          // Run cleanup if exists
          hook.cleanup.foreach(cleanup => cleanup())

          // Run effect and store new cleanup
          val result = hook.effect()
          hook.cleanup = result match {
            case cleanup: (() => Unit) => Some(cleanup)
            case _                     => None
          }
          hook.lastDeps = hook.deps
        case _ => // Not an effect hook
      }
    }
  }
}
