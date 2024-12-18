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
      "existingHooks" -> instance.hooks.length,
      "hooks"         -> instance.hooks.toString,
    ),
  )

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
        Map("hookIndex" -> instance.hookIndex.toString),
      )
      h.asInstanceOf[StateHook[T]]
    case None =>
      logger.debug("Creating new hook", category = "Hooks")
      createHook()
  }

  instance.hookIndex += 1

  logger.debug(
    "Returning hook value",
    category = "Hooks",
    Map(
      "hookIndex" -> instance.hookIndex.toString,
      "value"     -> hook.value.toString,
    ),
  )

  (hook.value, hook.setter)
}
