package io.github.edadma.fluxus

import scala.collection.mutable.ArrayBuffer

object RenderContext {
  // Stack of component instances (for nested components)
  private val instanceStack = new scala.collection.mutable.Stack[ComponentInstance]()

  // Stack to keep track of component indices for generating IDs
  private val indexStack = new scala.collection.mutable.Stack[Int]()

  // Current index at each level
  var currentIndex: Int = 0

  def push(instance: ComponentInstance): Unit = {
    instanceStack.push(instance)
    indexStack.push(currentIndex)
    currentIndex = 0 // Reset currentIndex for the child level
  }

  def pop(): Unit = {
    instanceStack.pop()
    currentIndex = indexStack.pop()
    currentIndex += 1 // Increment index for the next sibling
  }

  def currentInstance: ComponentInstance = {
    if (instanceStack.nonEmpty) instanceStack.top
    else throw new IllegalStateException("No component is currently rendering.")
  }

  // Generate a unique ID based on the current path
  def currentId: String = {
    (indexStack.toList.reverse :+ currentIndex).mkString("-")
  }
}
