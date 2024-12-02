package io.github.edadma.fluxus.core.hooks

import io.github.edadma.fluxus.core.dom.Reconciler
import io.github.edadma.fluxus.core.types.*
import io.github.edadma.fluxus.logging.Logger
import io.github.edadma.fluxus.logging.Logger.Category
import io.github.edadma.fluxus.error.HookValidationError
import org.scalajs.dom

sealed trait Hook[T] {
  def value: T
}

case class StateHook[T](
    private var currentValue: T,
    setState: (T | (T => T)) => Unit,
) extends Hook[T] {
  def value: T = currentValue
}

case class EffectHook(
    deps: Option[Seq[Any]],
    effectFn: () => (() => Unit),
    var cleanup: Option[() => Unit] = None,
) extends Hook[Unit] {
  def value: Unit = ()
}

object Hooks {
  private var currentInstance: Option[ComponentInstance] = None

  def setCurrentInstance(instance: ComponentInstance): Unit = {
    val opId = Logger.nextOperationId
    Logger.debug(
      Category.StateEffect,
      "Setting current instance",
      opId,
      Map(
        "newInstanceId"    -> instance.id,
        "previousInstance" -> currentInstance.map(_.id).getOrElse("none"),
        "instanceType"     -> instance.componentType,
        "hookCount"        -> instance.hooks.length,
        "isRendering"      -> instance.isRendering,
        "isInCleanup"      -> instance.isInCleanup,
      ),
    )
    currentInstance = Some(instance)
  }

  def clearCurrentInstance(): Unit = {
    val opId = Logger.nextOperationId
    currentInstance.foreach(instance =>
      Logger.debug(
        Category.StateEffect,
        "Clearing current instance",
        opId,
        Map(
          "instanceId"    -> instance.id,
          "componentType" -> instance.componentType,
          "hooksState" -> Map(
            "hookCount"        -> instance.hooks.length,
            "currentHookIndex" -> instance.hookIndex,
            "effectCount"      -> instance.effects.length,
            "hasEffectError"   -> instance.hasEffectError,
          ),
        ),
      ),
    )
    currentInstance = None
  }

  def useState[T](initialValue: T): (T, (T | (T => T)) => Unit) = {
    val opId = Logger.nextOperationId

    Logger.debug(
      Category.StateEffect,
      "useState called",
      opId,
      Map(
        "initialValue" -> initialValue,
        "valueType"    -> initialValue.getClass.getName,
      ),
    )

    val instance = currentInstance.getOrElse {
      val error = "useState must be called within a component"
      Logger.error(
        Category.StateEffect,
        error,
        opId,
        Map(
          "stackTrace" -> Thread.currentThread().getStackTrace.mkString("\n"),
        ),
      )
      throw HookValidationError(error, Map.empty, opId)
    }

    val hookIndex = instance.hookIndex

    // Get or create the hook
    val hook = if (hookIndex >= instance.hooks.length) {
      // First render - create new hook
      Logger.debug(
        Category.StateEffect,
        "Creating new state hook",
        opId,
        Map(
          "componentId"       -> instance.id,
          "hookIndex"         -> hookIndex,
          "initialValue"      -> initialValue,
          "existingHookCount" -> instance.hooks.length,
        ),
      )

      var currentValue = initialValue
      lazy val setter: (T | (T => T)) => Unit = (update: T | (T => T)) => {
        val updateOpId = Logger.nextOperationId

        if (instance.isRendering) {
          val error = "Cannot update state during render"
          Logger.error(
            Category.StateEffect,
            error,
            updateOpId,
            Map(
              "componentId"   -> instance.id,
              "componentType" -> instance.componentType,
              "hookIndex"     -> hookIndex,
              "stackTrace"    -> Thread.currentThread().getStackTrace.mkString("\n"),
            ),
          )
          throw HookValidationError(error, Map("componentId" -> instance.id), updateOpId)
        }

        if (instance.isInCleanup) {
          Logger.warn(
            Category.StateEffect,
            "State update during cleanup - skipped",
            updateOpId,
            Map(
              "componentId"   -> instance.id,
              "componentType" -> instance.componentType,
              "hookIndex"     -> hookIndex,
              "updateValue"   -> update,
              "stackTrace"    -> Thread.currentThread().getStackTrace.mkString("\n"),
            ),
          )
        } else {
          // Handle both direct value and function updates
          val newValue = update match {
            case f: (T => T) @unchecked => f(currentValue)
            case v: T @unchecked        => v
          }

          Logger.debug(
            Category.StateEffect,
            "State update",
            updateOpId,
            Map(
              "componentId"  -> instance.id,
              "hookIndex"    -> hookIndex,
              "oldValue"     -> currentValue,
              "newValue"     -> newValue,
              "updateType"   -> (if (update.isInstanceOf[(?) => ?]) "function" else "direct"),
              "stateVersion" -> instance.stateVersion,
            ),
          )

          val oldValue = currentValue
          currentValue = newValue
          instance.hooks = instance.hooks.updated(hookIndex, StateHook(currentValue, setter))
          instance.needsRender = true

          // Store old rendered output
          val oldRendered = instance.rendered

          // Get new rendered output
          val newRendered = instance.render(updateOpId)

          // Diff and update DOM
          instance.domNode.foreach { container =>
            Logger.debug(
              Category.StateEffect,
              "Updating DOM after state change",
              updateOpId,
              Map(
                "componentId"   -> instance.id,
                "componentType" -> instance.componentType,
              ),
            )
            Reconciler.diff(oldRendered, newRendered, container.asInstanceOf[dom.Element])
          }

          Logger.debug(
            Category.StateEffect,
            "State updated",
            updateOpId,
            Map(
              "componentId"   -> instance.id,
              "componentType" -> instance.componentType,
              "oldValue"      -> oldValue,
              "newValue"      -> newValue,
              "stateVersion"  -> instance.stateVersion,
              "needsRender"   -> instance.needsRender,
              "updateSource"  -> Thread.currentThread().getStackTrace.take(5).mkString("\n"),
            ),
          )
        }
      }

      val stateHook = StateHook(currentValue, setter)
      instance.hooks = instance.hooks :+ stateHook
      stateHook
    } else {
      // Subsequent renders - reuse existing hook
      val existingHook = instance.hooks(hookIndex).asInstanceOf[StateHook[T]]
      Logger.trace(
        Category.StateEffect,
        "Reusing existing state hook",
        opId,
        Map(
          "componentId"  -> instance.id,
          "hookIndex"    -> hookIndex,
          "currentValue" -> existingHook.value,
        ),
      )
      existingHook
    }

    instance.hookIndex += 1

    Logger.debug(
      Category.StateEffect,
      "useState complete",
      opId,
      Map(
        "componentId"   -> instance.id,
        "hookIndex"     -> (instance.hookIndex - 1),
        "returnedValue" -> hook.value,
        "totalHooksNow" -> instance.hooks.length,
      ),
    )

    (hook.value, hook.setState)
  }
  def useEffect(effectFn: () => (() => Unit), deps: Option[Seq[Any]] = None): Unit = {
    val opId = Logger.nextOperationId

    Logger.debug(
      Category.StateEffect,
      "useEffect called",
      opId,
      Map(
        "dependencies" -> deps.map(_.mkString(", ")).getOrElse("none"),
      ),
    )

    val instance = currentInstance.getOrElse {
      val error = "useEffect must be called within a component"
      Logger.error(
        Category.StateEffect,
        error,
        opId,
        Map(
          "stackTrace" -> Thread.currentThread().getStackTrace.mkString("\n"),
        ),
      )
      throw HookValidationError(error, Map.empty, opId)
    }

    Logger.trace(
      Category.StateEffect,
      "Component state for useEffect",
      opId,
      Map(
        "componentId"    -> instance.id,
        "componentType"  -> instance.componentType,
        "isRendering"    -> instance.isRendering,
        "hookIndex"      -> instance.hookIndex,
        "effectCount"    -> instance.effects.length,
        "hasEffectError" -> instance.hasEffectError,
      ),
    )

    val hookIndex = instance.hookIndex

    if (hookIndex >= instance.hooks.length) {
      // First render - create new hook
      Logger.debug(
        Category.StateEffect,
        "Creating new effect hook",
        opId,
        Map(
          "componentId"       -> instance.id,
          "hookIndex"         -> hookIndex,
          "hasDeps"           -> deps.isDefined,
          "existingHookCount" -> instance.hooks.length,
        ),
      )

      val effectHook = EffectHook(deps, effectFn)
      instance.hooks = instance.hooks :+ effectHook

      // Queue effect
      val effectIndex = instance.effects.length
      instance.effects = instance.effects :+ (() => {
        Logger.debug(
          Category.StateEffect,
          "Running effect",
          opId,
          Map(
            "componentId" -> instance.id,
            "hookIndex"   -> hookIndex,
            "effectIndex" -> effectIndex,
            "hasCleanup"  -> effectHook.cleanup.isDefined,
          ),
        )

        try {
          // Run cleanup if exists
          if (effectHook.cleanup.isDefined) {
            Logger.debug(
              Category.StateEffect,
              "Running effect cleanup",
              opId,
              Map(
                "componentId" -> instance.id,
                "hookIndex"   -> hookIndex,
                "effectIndex" -> effectIndex,
              ),
            )
            runEffectCleanup(effectHook, opId)
          }

          // Run effect and store cleanup
          Logger.trace(
            Category.StateEffect,
            "Executing effect function",
            opId,
            Map(
              "componentId" -> instance.id,
              "hookIndex"   -> hookIndex,
              "effectIndex" -> effectIndex,
            ),
          )

          val cleanupFn = effectFn()
          effectHook.cleanup = Some(cleanupFn)

          Logger.debug(
            Category.StateEffect,
            "Effect executed successfully",
            opId,
            Map(
              "componentId" -> instance.id,
              "hookIndex"   -> hookIndex,
              "effectIndex" -> effectIndex,
              "hasCleanup"  -> effectHook.cleanup.isDefined,
            ),
          )
        } catch {
          case error: Throwable =>
            Logger.error(
              Category.StateEffect,
              "Effect execution failed",
              opId,
              Map(
                "componentId" -> instance.id,
                "hookIndex"   -> hookIndex,
                "effectIndex" -> effectIndex,
                "error"       -> error.getMessage,
                "errorType"   -> error.getClass.getName,
                "stackTrace"  -> error.getStackTrace.mkString("\n"),
              ),
            )
            instance.hasEffectError = true
        }
      })
    } else {
      // Subsequent renders - check deps
      val existingHook = instance.hooks(hookIndex).asInstanceOf[EffectHook]

      Logger.trace(
        Category.StateEffect,
        "Checking effect dependencies",
        opId,
        Map(
          "componentId" -> instance.id,
          "hookIndex"   -> hookIndex,
          "oldDeps"     -> existingHook.deps.map(_.mkString(", ")).getOrElse("none"),
          "newDeps"     -> deps.map(_.mkString(", ")).getOrElse("none"),
        ),
      )

      if (depsChanged(existingHook.deps, deps)) {
        Logger.debug(
          Category.StateEffect,
          "Effect dependencies changed",
          opId,
          Map(
            "componentId" -> instance.id,
            "hookIndex"   -> hookIndex,
            "oldDeps"     -> existingHook.deps.map(_.mkString(", ")).getOrElse("none"),
            "newDeps"     -> deps.map(_.mkString(", ")).getOrElse("none"),
          ),
        )

        // Queue new effect
        val effectIndex = instance.effects.length
        instance.effects = instance.effects :+ (() => {
          try {
            if (existingHook.cleanup.isDefined) {
              Logger.debug(
                Category.StateEffect,
                "Running cleanup before effect re-run",
                opId,
                Map(
                  "componentId" -> instance.id,
                  "hookIndex"   -> hookIndex,
                  "effectIndex" -> effectIndex,
                ),
              )
              runEffectCleanup(existingHook, opId)
            }

            Logger.debug(
              Category.StateEffect,
              "Re-running effect with new dependencies",
              opId,
              Map(
                "componentId" -> instance.id,
                "hookIndex"   -> hookIndex,
                "effectIndex" -> effectIndex,
              ),
            )

            val cleanupFn = effectFn()
            existingHook.cleanup = Some(cleanupFn)

            Logger.debug(
              Category.StateEffect,
              "Effect re-run complete",
              opId,
              Map(
                "componentId"   -> instance.id,
                "hookIndex"     -> hookIndex,
                "effectIndex"   -> effectIndex,
                "hasNewCleanup" -> existingHook.cleanup.isDefined,
              ),
            )
          } catch {
            case error: Throwable =>
              Logger.error(
                Category.StateEffect,
                "Effect re-run failed",
                opId,
                Map(
                  "componentId" -> instance.id,
                  "hookIndex"   -> hookIndex,
                  "effectIndex" -> effectIndex,
                  "error"       -> error.getMessage,
                  "errorType"   -> error.getClass.getName,
                  "stackTrace"  -> error.getStackTrace.mkString("\n"),
                ),
              )
              instance.hasEffectError = true
          }
        })
      } else {
        Logger.trace(
          Category.StateEffect,
          "Effect dependencies unchanged - skipping re-run",
          opId,
          Map(
            "componentId" -> instance.id,
            "hookIndex"   -> hookIndex,
          ),
        )
      }

      // Update deps
      instance.hooks = instance.hooks.updated(hookIndex, existingHook.copy(deps = deps))
    }

    instance.hookIndex += 1

    Logger.debug(
      Category.StateEffect,
      "useEffect setup complete",
      opId,
      Map(
        "componentId"        -> instance.id,
        "hookIndex"          -> (instance.hookIndex - 1),
        "totalHooksNow"      -> instance.hooks.length,
        "totalEffectsQueued" -> instance.effects.length,
      ),
    )
  }

  private def runEffectCleanup(effect: EffectHook, opId: Int): Unit = {
    effect.cleanup.foreach { cleanup =>
      try {
        Logger.debug(
          Category.StateEffect,
          "Running effect cleanup",
          opId,
          Map("hasCleanup" -> true),
        )
        cleanup()
        Logger.debug(
          Category.StateEffect,
          "Effect cleanup completed successfully",
          opId,
        )
      } catch {
        case error: Throwable =>
          Logger.error(
            Category.StateEffect,
            "Effect cleanup error",
            opId,
            Map(
              "error"      -> error.getMessage,
              "errorType"  -> error.getClass.getName,
              "stackTrace" -> error.getStackTrace.mkString("\n"),
            ),
          )
      }
    }
    effect.cleanup = None
  }

  private def depsChanged(oldDeps: Option[Seq[Any]], newDeps: Option[Seq[Any]]): Boolean = {
    val opId = Logger.nextOperationId
    Logger.trace(
      Category.StateEffect,
      "Checking effect dependencies for changes",
      opId,
      Map(
        "oldDeps" -> oldDeps.map(_.mkString(", ")).getOrElse("none"),
        "newDeps" -> newDeps.map(_.mkString(", ")).getOrElse("none"),
      ),
    )

    val changed = (oldDeps, newDeps) match {
      case (None, None)      => true // No deps array provided - run every time
      case (Some(old), None) => true // Changed from deps to no deps - run
      case (None, Some(_))   => true // Changed from no deps to deps - run
      case (Some(old), Some(current)) =>
        if (old.length != current.length) {
          Logger.debug(
            Category.StateEffect,
            "Dependencies changed - different lengths",
            opId,
            Map(
              "oldLength" -> old.length,
              "newLength" -> current.length,
            ),
          )
          true
        } else {
          val changes = old.zip(current).zipWithIndex.filter { case ((a, b), _) => a != b }
          if (changes.nonEmpty) {
            Logger.debug(
              Category.StateEffect,
              "Dependencies changed - values differ",
              opId,
              Map(
                "changedIndices" -> changes.map(_._2).mkString(", "),
                "oldValues"      -> changes.map(_._1._1).mkString(", "),
                "newValues"      -> changes.map(_._1._2).mkString(", "),
              ),
            )
          }
          changes.nonEmpty
        }
    }

    Logger.trace(
      Category.StateEffect,
      "Dependency check complete",
      opId,
      Map("changed" -> changed),
    )

    changed
  }
}
