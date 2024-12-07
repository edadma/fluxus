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

    // Always create hook if none exist, otherwise reuse
    val hook = if (hookIndex >= instance.hooks.length) {
      // First hook creation
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
          Logger.error(Category.StateEffect, error, updateOpId)
          throw HookValidationError(error, Map("componentId" -> instance.id), updateOpId)
        }

        if (instance.isInCleanup) {
          Logger.warn(Category.StateEffect, "State update during cleanup - skipped", updateOpId)
        } else if (!instance.isUpdating) {
          instance.isUpdating = true
          try {
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

            // Store old rendered output
            val oldRendered = instance.rendered

            // Get new rendered output
            val newRendered = instance.render(updateOpId)

            // Find container element
            def findContainer: Option[dom.Element] = {
              def isElement(node: dom.Node): Boolean =
                node != null && node.nodeType == 1 // 1 is ELEMENT_NODE

              oldRendered.flatMap(_.domNode).flatMap(node =>
                Option(node.parentNode).filter(isElement).map(_.asInstanceOf[dom.Element]),
              ).orElse {
                instance.domNode.flatMap(node =>
                  Option(node.parentNode).filter(isElement).map(_.asInstanceOf[dom.Element]),
                )
              }
            }

            findContainer match {
              case Some(elem) =>
                Logger.debug(
                  Category.StateEffect,
                  "Updating DOM",
                  updateOpId,
                  Map(
                    "containerType"       -> elem.nodeName,
                    "containerChildCount" -> elem.childNodes.length,
                    "componentId"         -> instance.id,
                    "componentType"       -> instance.componentType,
                  ),
                )

                // Update DOM through the component instance to ensure effects run
                diff(
                  Some(ComponentNode(instance.component, instance.props, Some(instance), None)),
                  Some(ComponentNode(instance.component, instance.props, Some(instance), None)),
                  elem,
                )

              case None =>
                Logger.error(
                  Category.StateEffect,
                  "No DOM element found for update",
                  updateOpId,
                  Map(
                    "componentId"      -> instance.id,
                    "hasRendered"      -> oldRendered.isDefined,
                    "hasInstance"      -> instance.domNode.isDefined,
                    "renderNodeType"   -> oldRendered.map(_.getClass.getSimpleName).getOrElse("none"),
                    "instanceNodeType" -> instance.domNode.map(_.nodeName).getOrElse("none"),
                  ),
                )
            }

            Logger.debug(
              Category.StateEffect,
              "State updated",
              updateOpId,
              Map(
                "componentId"   -> instance.id,
                "componentType" -> instance.componentType,
                "oldValue"      -> oldValue,
                "needsRender"   -> instance.needsRender,
                "newValue"      -> newValue,
                "stateVersion"  -> instance.stateVersion,
              ),
            )
          } finally {
            instance.isUpdating = false
          }
        }
      }

      val stateHook = StateHook(currentValue, setter)
      instance.hooks = instance.hooks :+ stateHook
      stateHook
    } else {
      instance.hooks(hookIndex).asInstanceOf[StateHook[T]]
    }

    instance.hookIndex += 1

    Logger.debug(
      Category.StateEffect,
      "useState complete",
      opId,
      Map(
        "componentId"   -> instance.id,
        "hookIndex"     -> hookIndex,
        "returnedValue" -> hook.value,
        "totalHooksNow" -> instance.hooks.length,
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

        // Queue effect with cleanup
        instance.effects = instance.effects :+ (() => {
          // Run cleanup using the helper function
          runEffectCleanup(existingHook, opId)

          try {
            // Run new effect
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
        })
      } else {
        Logger.debug(
          Category.StateEffect,
          "Dependencies unchanged - skipping effect",
          opId,
          Map(
            "componentId" -> instance.id,
            "hookIndex"   -> hookIndex,
          ),
        )
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
