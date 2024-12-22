package io.github.edadma.fluxus

import io.github.edadma.fluxus.core.{BatchScheduler, ComponentInstance}

sealed trait Hook
case class StateHook[T](
    var value: T,
    var setter: (T | (T => T)) => Unit,
) extends Hook:
  override def toString: String = s"StateHook($value)"

def useState[T](initial: T): (T, (T | (T => T)) => Unit) = {
  val instance = ComponentInstance.current.getOrElse(
    throw new Error("Hooks must be called within component render"),
  )

  logger.debug(
    "useState called",
    category = "Hooks",
    Map(
      "instance"      -> instance.id,
      "hookIndex"     -> instance.hookIndex.toString,
      "initialValue"  -> initial.toString,
      "hooks"         -> instance.hooks.toString,
      "totalHooks"    -> instance.hooks.length.toString,
      "isFirstRender" -> (instance.hooks.isEmpty).toString,
    ),
  )

  // During render, we should never try to access a hook index
  // that's beyond what we had in the previous render
  if (instance.hooks.nonEmpty && instance.hookIndex > instance.hooks.length) {
    throw new Error(
      "Hook called conditionally. Hooks must be called in the exact same order on every render.",
    )
  }

  def createHook(): StateHook[T] = {
    val hook = new StateHook[T](
      value = initial,
      setter = null, // Placeholder
    )

    // Create a stable setter function that handles both cases
    val setter = (update: T | (T => T)) => {
      logger.debug(
        "Setter called",
        category = "Hooks",
        Map(
          "currentValue" -> hook.value.toString,
          "updateType"   -> (if (update.isInstanceOf[Function1[?, ?]]) "function" else "value"),
        ),
      )

      update match {
        case f: (T => T) @unchecked =>
          BatchScheduler.scheduleFunctionalUpdate(instance, hook, f)
        case value: T @unchecked =>
          BatchScheduler.scheduleUpdate(instance, hook, value)
      }
    }

    hook.setter = setter
    instance.hooks = instance.hooks :+ hook
    hook
  }

  val hook = instance.hooks.lift(instance.hookIndex) match {
    case Some(h: StateHook[_]) =>
      logger.debug(
        "Reusing existing hook",
        category = "Hooks",
        Map(
          "hookIndex"    -> instance.hookIndex.toString,
          "currentValue" -> h.value.toString,
          "allHooks"     -> instance.hooks.toString,
        ),
      )
      h.asInstanceOf[StateHook[T]]
    case Some(_: EffectHook) =>
      throw new Error(s"Hook mismatch: expected StateHook but found EffectHook at index ${instance.hookIndex}")
    case None =>
      logger.debug("Creating new hook", category = "Hooks")

      createHook()
  }

  instance.hookIndex += 1

  logger.debug(
    "Returning hook value",
    category = "Hooks",
    Map(
      "hookIndex"   -> instance.hookIndex.toString,
      "hookValue"   -> hook.value.toString, // Add this
      "returnValue" -> hook.value.toString, // Add this
    ),
  )

  (hook.value, hook.setter)
}

case class EffectHook(
    effect: () => (() => Unit) | Unit, // Effect fn returning optional cleanup
    deps: Seq[Any],                    // Dependencies (null means run every time)
    var cleanup: Option[() => Unit],   // Last cleanup function if any
    var lastDeps: Seq[Any],            // Previous deps for comparison
) extends Hook
