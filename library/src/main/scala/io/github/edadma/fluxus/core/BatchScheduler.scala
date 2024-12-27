package io.github.edadma.fluxus.core

import io.github.edadma.fluxus.{EffectHook, StateHook, logger}
import org.scalajs.dom

import scala.annotation.tailrec
import scala.collection.mutable
import scala.scalajs.js

/** Handles batching of state updates to prevent unnecessary re-renders and ensure consistent state updates.
  */
object BatchScheduler {
  private var pendingEffects = Set[ComponentInstance]()

  def scheduleEffects(instance: ComponentInstance): Unit = {
    logger.debug(
      "Scheduling effects for component",
      category = "BatchScheduler",
      Map("instanceId" -> instance.id),
    )
    pendingEffects += instance
  }

  def flushEffects(): Unit = {
    if (pendingEffects.nonEmpty) {
      logger.debug(
        "Flushing effects",
        category = "BatchScheduler",
        Map("count" -> pendingEffects.size.toString),
      )
      handleEffects(pendingEffects)
      pendingEffects = Set()
    }
  }

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
        "batchSize" -> batch.size.toString,
        "updates" -> batch.map(u =>
          s"instance=${Option(u.instance).map(_.id).getOrElse("null")}, " +
            s"hook=${Option(u.hook).map(_.toString).getOrElse("null")}, " +
            s"fn=${Option(u.updateFn).map(_.toString).getOrElse("null")}",
        ).mkString("; "),
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
      if (update.instance == null) {
        logger.error(
          "Null instance in state update",
          category = "BatchScheduler",
          Map(
            "hook"     -> Option(update.hook).map(_.toString).getOrElse("null"),
            "updateFn" -> Option(update.updateFn).map(_.toString).getOrElse("null"),
          ),
        )
        sys.error("Null instance in state update")
      }

      logger.debug(
        "Processing update",
        category = "BatchScheduler",
        Map(
          "instanceId" -> update.instance.id,
          "hookValue"  -> Option(update.hook.value).map(_.toString).getOrElse("null"),
        ),
      )

      val oldValue = update.hook.value
      val newValue = update.updateFn(oldValue)

      logger.debug(
        "After value update",
        category = "BatchScheduler",
        Map(
          "hook"       -> update.hook.toString,
          "instanceId" -> update.instance.id,
          "oldValue"   -> oldValue.toString,
          "newValue"   -> newValue.toString,
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
        "count" -> componentsToUpdate.size.toString,
        "components" -> componentsToUpdate.map(c =>
          s"id=${c.id}, hooks=${c.hooks.length}, mounted=${c.rendered.isDefined}",
        ).mkString("; "),
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

      if (instance.rendered.isEmpty) {
        logger.warn(
          "Attempting to rerender unmounted component",
          category = "BatchScheduler",
          Map("instanceId" -> instance.id),
        )
      }

      instance.rerender()
    }

    // Run effects for updated components
    logger.debug(
      "Running effects",
      category = "BatchScheduler",
      Map(
        "componentCount" -> componentsToUpdate.size.toString,
        "components"     -> componentsToUpdate.map(_.id).mkString(", "),
      ),
    )

    // Run effects for updated components
    flushEffects()
  }

  def handleEffects(components: Set[ComponentInstance]): Unit = {
    logger.debug(
      "Starting handleEffects",
      category = "BatchScheduler",
      Map(
        "componentCount" -> components.size.toString,
        "components"     -> components.map(_.id).mkString(", "),
      ),
    )

    // Sort components so parents run before children
    val orderedComponents = components.toSeq.sortWith { (a, b) =>
//      def isParentOf(parent: ComponentInstance, child: ComponentInstance): Boolean = {
//        @scala.annotation.tailrec
//        def checkParent(current: Option[ComponentInstance]): Boolean = {
//          current match {
//            case None                                 => false
//            case Some(instance) if instance == parent => true
//            case Some(instance)                       => checkParent(instance.parent)
//          }
//        }
//
//        checkParent(child.parent)
//      }
//
//      isParentOf(a, b)
//    }
      def isAncestorOf(ancestor: ComponentInstance, descendant: ComponentInstance): Boolean = {
        @scala.annotation.tailrec
        def checkAncestors(current: Option[ComponentInstance]): Boolean = {
          current match {
            case None                                   => false
            case Some(instance) if instance == ancestor => true
            case Some(instance)                         => checkAncestors(instance.parent)
          }
        }
        checkAncestors(descendant.parent)
      }

      // Order based on ancestor/descendant relationship:
      // - If a is ancestor of b: a comes first (return true)
      // - If b is ancestor of a: b comes first (return false)
      // - Otherwise: order doesn't matter
      if (isAncestorOf(a, b)) true
      else if (isAncestorOf(b, a)) false
      else a.id < b.id // Stable sort for unrelated components
    }

    logger.debug(
      "Ordered components for effects",
      category = "BatchScheduler",
      Map(
        "order" -> orderedComponents.map(_.id).mkString(", "),
      ),
    )

    orderedComponents.foreach { instance =>
      logger.debug(
        "Processing effects for component",
        category = "BatchScheduler",
        Map(
          "instanceId" -> instance.id,
          "hookCount"  -> instance.hooks.length.toString,
          "hooks"      -> instance.hooks.map(_.toString).mkString(", "),
        ),
      )

      // First collect all hooks that need cleanup
      val hooksNeedingCleanup = instance.hooks.collect {
        case hook: EffectHook
            if hook.lastDeps != null &&
              hook.deps != hook.lastDeps &&
              hook.cleanup.isDefined => hook
      }

      // Run cleanups in reverse order
      hooksNeedingCleanup.reverse.foreach { hook =>
        logger.debug("Running cleanup before effect re-run", category = "BatchScheduler")
        hook.cleanup.get()
      }

      instance.hooks.foreach {
        case hook: EffectHook =>
          try {
            logger.debug(
              "Found effect hook",
              category = "BatchScheduler",
              Map(
                "hookDeps"   -> Option(hook.deps).map(_.mkString(", ")).getOrElse("null"),
                "lastDeps"   -> Option(hook.lastDeps).map(_.mkString(", ")).getOrElse("null"),
                "hasCleanup" -> hook.cleanup.isDefined.toString,
              ),
            )

            val shouldRun = hook.deps == null ||
              hook.lastDeps != hook.deps ||
              hook.cleanup.isEmpty

            logger.debug(
              "Checking effect dependencies",
              category = "BatchScheduler",
              Map(
                "lastDeps"  -> Option(hook.lastDeps).map(_.mkString(", ")).getOrElse("null"),
                "newDeps"   -> Option(hook.deps).map(_.mkString(", ")).getOrElse("null"),
                "areEqual"  -> (hook.lastDeps == hook.deps).toString,
                "shouldRun" -> shouldRun.toString,
              ),
            )

            if (shouldRun) {
              logger.debug(
                "Running effect",
                category = "BatchScheduler",
                Map(
                  "instanceId" -> instance.id,
                  "hasDeps"    -> (hook.deps != null).toString,
                  "hasCleanup" -> hook.cleanup.isDefined.toString,
                ),
              )

              // Only run cleanup if deps have changed (not on initial render)
              if (hook.lastDeps != null && hook.deps != hook.lastDeps) {
                hook.cleanup.foreach { cleanup =>
                  logger.debug("Running cleanup before effect re-run", category = "BatchScheduler")
                  cleanup()
                }
              }

              // Run effect and store new cleanup
              val result = hook.effect()
              hook.cleanup = result match {
                case cleanup: (() => Unit) => Some(cleanup)
                case _                     => None
              }
              hook.lastDeps = hook.deps

              logger.debug(
                "Effect complete",
                category = "BatchScheduler",
                Map(
                  "hasNewCleanup" -> hook.cleanup.isDefined.toString,
                ),
              )
            } else {
              logger.debug("Skipping effect - no changes", category = "BatchScheduler")
            }
          } catch {
            case e: Throwable =>
              logger.error(
                "Error in effect",
                category = "BatchScheduler",
                Map("error" -> e.toString),
              )
              throw e
          }
        case _ => // Not an effect hook
          logger.debug("Non-effect hook found", category = "BatchScheduler")
      }
    }
  }
}
