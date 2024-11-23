// RenderContext.scala

package io.github.edadma.fluxus

import scala.collection.mutable.ArrayBuffer

object RenderContext {
  private val instanceStack   = new scala.collection.mutable.Stack[ComponentInstance]()
  var componentIdCounter: Int = 0 // Counter for assigning IDs

  def push(instance: ComponentInstance): Unit = {
    instanceStack.push(instance)
  }

  def pop(): Unit = {
    instanceStack.pop()
  }

  def currentInstance: ComponentInstance = {
    if (instanceStack.nonEmpty) instanceStack.top
    else throw new IllegalStateException("No component is currently rendering.")
  }
}
