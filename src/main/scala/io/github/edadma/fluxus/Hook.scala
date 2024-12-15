package io.github.edadma.fluxus

import io.github.edadma.fluxus.core.ComponentInstance

sealed trait Hook
case class StateHook[T](
    var value: T,
    setter: T => Unit,
) extends Hook

// Minimal useState to get tests compiling
def useState[T](initial: T): (T, T => Unit) = {
  val instance = ComponentInstance.current.getOrElse(
    throw new Error("Hooks must be called within component render"),
  )

  // Get existing or create new hook at current index
  val hook = instance.hooks.lift(instance.hookIndex) match {
    case Some(h: StateHook[T]) => h
    case None                  =>
      // For now, setter does nothing - we'll implement BatchScheduler next
      val hook = StateHook(
        value = initial,
        setter = _ => (), // No-op setter for now
      )
      instance.hooks = instance.hooks :+ hook
      hook
  }

  instance.hookIndex += 1

  (hook.value, hook.setter)
}
