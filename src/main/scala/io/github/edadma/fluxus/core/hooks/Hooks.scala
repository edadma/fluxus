package io.github.edadma.fluxus.core.hooks

import io.github.edadma.fluxus.core.dom.Reconciler
import io.github.edadma.fluxus.core.dom.Reconciler.diff
import io.github.edadma.fluxus.core.types.*
import io.github.edadma.fluxus.logging.Logger
import io.github.edadma.fluxus.logging.Logger.Category
import io.github.edadma.fluxus.error.HookValidationError
import org.scalajs.dom

sealed trait Hook[T] {
  def value: T
}

case class StateHook[T](
    var currentValue: T,
    var setState: (T | (T => T)) => Unit = null,
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
    currentInstance.foreach(instance =>
      Logger.debug(
        Category.StateEffect,
        "Clearing current instance",
        Logger.nextOperationId,
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
      Map("initialValue" -> initialValue, "valueType" -> initialValue.getClass.getName),
    )

    val instance = currentInstance.getOrElse {
      throw new Error("useState must be called within a component")
    }

    val hookIndex = instance.hookIndex

    val hook = if (hookIndex < instance.hooks.length) {
      Logger.debug(
        Category.StateEffect,
        "Reusing existing state hook",
        opId,
        Map("componentId" -> instance.id, "hookIndex" -> hookIndex),
      )
      instance.hooks(hookIndex).asInstanceOf[StateHook[T]]
    } else {
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

      val newHook = StateHook[T](initialValue)

      newHook.setState = (update: T | (T => T)) => {
        val updateOpId = Logger.nextOperationId

        Logger.debug(
          Category.StateEffect,
          "State update triggered",
          updateOpId,
          Map(
            "componentId"   -> instance.id,
            "componentType" -> instance.componentType,
            "currentValue"  -> newHook.value,
            "updateType"    -> (if (update.isInstanceOf[Function1[_, _]]) "function" else "value"),
          ),
        )

        if (instance.isInCleanup) {
          Logger.warn(Category.StateEffect, "State update during cleanup - skipped", updateOpId)
        } else if (instance.isRendering) {
          Logger.error(Category.StateEffect, "Cannot update state during render", updateOpId)
          throw new Error("Cannot update state during render")
        } else {
          // Calculate new value
          val newValue = update match {
            case f: Function1[_, _] => f.asInstanceOf[T => T](newHook.currentValue)
            case value              => value.asInstanceOf[T]
          }

          Logger.debug(
            Category.StateEffect,
            "Applying state update",
            updateOpId,
            Map("oldValue" -> newHook.currentValue, "newValue" -> newValue),
          )

          newHook.currentValue = newValue
          instance.needsRender = true

          // Important: Set isUpdating before triggering re-render
          instance.isUpdating = true

          // Re-render with proper component context
          instance.rendered.foreach { rendered =>
            instance.domNode.foreach { element =>
              val newRendered = instance.render(updateOpId)

              Reconciler.diff(
                Some(rendered),
                newRendered,
                element.asInstanceOf[dom.Element],
              )

              instance.rendered = newRendered
            }
          }
        }
      }

      instance.hooks = instance.hooks :+ newHook
      newHook
    }

    instance.hookIndex += 1

    Logger.debug(
      Category.StateEffect,
      "useState complete",
      opId,
      Map(
        "isReused"      -> (hookIndex < instance.hooks.length),
        "componentId"   -> instance.id,
        "totalHooksNow" -> instance.hooks.length,
        "hookIndex"     -> hookIndex,
        "returnedValue" -> hook.value,
      ),
    )

    (hook.value, hook.setState)
  }

  def useEffect(effectFn: () => (() => Unit), deps: Seq[Any] | Null = null): Unit = {
    val depsOption = Option(deps)
    val opId       = Logger.nextOperationId

    Logger.debug(
      Category.StateEffect,
      "useEffect called",
      opId,
      Map(
        "dependencies" -> depsOption.map(_.mkString(", ")).getOrElse("none"),
      ),
    )

    val instance = currentInstance.getOrElse {
      val error = "useEffect must be called within a component"
      Logger.error(Category.StateEffect, error, opId)
      throw new Error(error)
    }

    val hookIndex = instance.hookIndex

    if (hookIndex >= instance.hooks.length) {
      // First time this hook is being used
      Logger.debug(
        Category.StateEffect,
        "Creating new effect hook",
        opId,
        Map(
          "componentId" -> instance.id,
          "hookIndex"   -> hookIndex,
          "hasDeps"     -> depsOption.isDefined,
        ),
      )

      val effectHook = EffectHook(depsOption, effectFn)
      instance.hooks = instance.hooks :+ effectHook

      // Queue initial effect
      instance.effects = instance.effects :+ (() => {
        Logger.debug(
          Category.StateEffect,
          "Running initial effect",
          opId,
          Map(
            "componentId" -> instance.id,
            "hookIndex"   -> hookIndex,
            "hasCleanup"  -> effectHook.cleanup.isDefined,
          ),
        )

        try {
          val cleanupFn = effectFn()
          effectHook.cleanup = Some(cleanupFn)
        } catch {
          case error: Throwable =>
            Logger.error(
              Category.StateEffect,
              "Effect execution failed",
              opId,
              Map(
                "componentId" -> instance.id,
                "error"       -> error.getMessage,
              ),
            )
            instance.hasEffectError = true
        }
      })
    } else {
      // Hook already exists - check if we need to re-run
      val existingHook = instance.hooks(hookIndex).asInstanceOf[EffectHook]

      val shouldRerun = depsChanged(existingHook.deps, depsOption)

      if (shouldRerun) {
        Logger.debug(
          Category.StateEffect,
          "Dependencies changed - queuing effect re-run",
          opId,
          Map(
            "componentId" -> instance.id,
            "hookIndex"   -> hookIndex,
            "oldDeps"     -> existingHook.deps.map(_.mkString(", ")).getOrElse("none"),
            "newDeps"     -> depsOption.map(_.mkString(", ")).getOrElse("none"),
          ),
        )

        // Create cleanup and new effect function
        val rerunEffect = () => {
          // Run cleanup of old effect
          existingHook.cleanup.foreach { cleanup =>
            try {
              Logger.debug(Category.StateEffect, "Running effect cleanup", opId)
              cleanup()
            } catch {
              case error: Throwable =>
                Logger.error(
                  Category.StateEffect,
                  "Effect cleanup error",
                  opId,
                  Map("error" -> error.getMessage),
                )
            }
          }

          // Run new effect
          try {
            Logger.debug(
              Category.StateEffect,
              "Running updated effect",
              opId,
              Map("componentId" -> instance.id),
            )
            val cleanupFn = effectFn()
            existingHook.cleanup = Some(cleanupFn)
          } catch {
            case error: Throwable =>
              Logger.error(
                Category.StateEffect,
                "Effect re-run failed",
                opId,
                Map("error" -> error.getMessage),
              )
              instance.hasEffectError = true
          }
        }

        // Add the effect to the instance's effect queue
        instance.effects = instance.effects :+ rerunEffect
      }

      // Update stored deps
      instance.hooks = instance.hooks.updated(
        hookIndex,
        existingHook.copy(deps = depsOption),
      )
    }

    instance.hookIndex += 1
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
      case (None, None)    => true // No deps array provided - run every time
      case (Some(_), None) => true // Changed from deps to no deps - run
      case (None, Some(_)) => true // Changed from no deps to deps - run
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

  private def runEffectCleanup(effect: EffectHook, opId: Int): Unit = {
    effect.cleanup.foreach { cleanup =>
      try {
        Logger.debug(Category.StateEffect, "Running effect cleanup", opId)
        cleanup()
        Logger.debug(Category.StateEffect, "Effect cleanup completed successfully", opId)
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
}
