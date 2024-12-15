package io.github.edadma.fluxus

import io.github.edadma.fluxus.core.{BatchScheduler, ComponentInstance}

sealed trait Hook
case class StateHook[T](
    var value: T,
    var setter: T => Unit,
) extends Hook:
  override def toString: String = s"StateHook($value)"

//def useState[T](initial: T): (T, T => Unit) = {
//  val instance = ComponentInstance.current.getOrElse(
//    throw new Error("Hooks must be called within component render"),
//  )
//
//  logger.debug(
//    "useState called",
//    category = "Hooks",
//    Map(
//      "instance"     -> instance.id,
//      "hookIndex"    -> instance.hookIndex.toString,
//      "initialValue" -> initial.toString,
//    ),
//  )
//
//  def createHook(): StateHook[T] = {
//    val hook = StateHook[T](
//      value = initial,
//      setter = _ => (), // Temporary setter
//    )
//
//    // Update the setter after hook creation
//    hook.setter = value => BatchScheduler.scheduleUpdate(instance, hook, value)
//
//    instance.hooks = instance.hooks :+ hook
//    hook
//  }
//
//  val hook = instance.hooks.lift(instance.hookIndex) match {
//    case Some(h: StateHook[_]) =>
//      h.asInstanceOf[StateHook[T]]
//    case None => createHook()
//    case Some(_) =>
//      throw new Error(s"Hook mismatch at index ${instance.hookIndex}")
//  }
//
//  instance.hookIndex += 1
//
//  (hook.value, hook.setter)
//}

def useState[T](initial: T): (T, T => Unit) = {
  val instance = ComponentInstance.current.getOrElse(
    throw new Error("Hooks must be called within component render"),
  )

  logger.debug(
    "useState called",
    category = "Hooks",
    Map(
      "instance"     -> instance.id,
      "hookIndex"    -> instance.hookIndex.toString,
      "initialValue" -> initial.toString,
    ),
  )

  def createHook(): StateHook[T] = {
    val hook = new StateHook[T](
      value = initial,
      setter = null, // Placeholder
    )
    // Create the stable setter function once
    val setter = (value: T) => BatchScheduler.scheduleUpdate(instance, hook, value)
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
    case Some(_) =>
      throw new Error(s"Hook mismatch at index ${instance.hookIndex}")
  }

  instance.hookIndex += 1

  // Just return the existing values
  (hook.value, hook.setter)
}
