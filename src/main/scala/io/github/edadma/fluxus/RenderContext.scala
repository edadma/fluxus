package io.github.edadma.fluxus

import scala.collection.mutable.ArrayBuffer

object RenderContext {
  private var currentInstance: Option[ComponentInstance] = None

  def set(instance: ComponentInstance): Unit = currentInstance = Some(instance)
  def clear(): Unit                          = currentInstance = None

  def current: ComponentInstance =
    currentInstance.getOrElse(
      throw new IllegalStateException("No component is currently rendering."),
    )
}

case class ComponentInstance(
    render: () => VNode,
    hooks: ArrayBuffer[Any] = ArrayBuffer.empty,
) {
  private var hookIndex: Int = 0

  def nextHook[T]: T = {
    val hook = hooks(hookIndex).asInstanceOf[T]
    hookIndex += 1
    hook
  }

  def addHook[T](hook: T): Unit = {
    hooks += hook
    hookIndex += 1
  }

  def resetHooks(): Unit = hookIndex = 0
}
