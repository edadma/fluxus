package io.github.edadma.fluxus

import scala.collection.mutable.ArrayBuffer

object RenderContext {
  var currentInstance: Option[ComponentInstance] = None

  def set(instance: ComponentInstance): Unit = {
    println(s"Setting current component instance")
    currentInstance = Some(instance)
  }

  def clear(): Unit = {
    println("Clearing current component instance")
    currentInstance = None
  }

  def current: ComponentInstance =
    currentInstance.getOrElse(
      throw new IllegalStateException("No component is currently rendering."),
    )
}

case class ComponentInstance(
    render: () => VNode,
    hooks: scala.collection.mutable.ArrayBuffer[Any] = scala.collection.mutable.ArrayBuffer.empty,
) {
  private[fluxus] var hookIndex: Int = 0

  def nextHook[T]: T = {
    println(s"DEBUG: Attempting to access nextHook at index ${hookIndex} (total hooks: ${hooks.size})")
    if (hookIndex >= hooks.size) {
      throw new IllegalStateException(
        s"Invalid hook access: hookIndex=${hookIndex}, total hooks=${hooks.size}",
      )
    }
    val hook = hooks(hookIndex).asInstanceOf[T]
    hookIndex += 1
    println(s"DEBUG: Hook index incremented to $hookIndex after accessing hook")
    hook
  }

  def addHook[T](hook: T): Unit = {
    hooks += hook
    println(s"Added hook: $hook (total hooks: ${hooks.size})")
  }

  def resetHooks(): Unit = {
    println("Resetting hookIndex to 0")
    hookIndex = 0
  }
}
