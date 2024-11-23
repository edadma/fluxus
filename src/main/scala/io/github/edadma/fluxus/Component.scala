package io.github.edadma.fluxus

import scala.collection.mutable.ArrayBuffer

// Represents an instance of a component with its own hooks (state)
case class ComponentInstance(
    renderFunction: Props => VNode,                 // The component function that returns a VNode
    var props: Props,                               // The props passed to the component
    var hooks: ArrayBuffer[Any] = ArrayBuffer.empty, // The hooks (state) used by the component
) {
  // Index to keep track of the current hook during rendering
  var hookIndex: Int = 0

  // Resets the hook index before rendering
  def resetHooks(): Unit = {
    hookIndex = 0
  }
}

// The render context keeps track of the current component instance being rendered
object RenderContext {
  // The stack of component instances (for nested components)
  private val instanceStack = new scala.collection.mutable.Stack[ComponentInstance]()

  // Pushes a component instance onto the stack
  def push(instance: ComponentInstance): Unit = {
    instanceStack.push(instance)
  }

  // Pops the current component instance from the stack
  def pop(): Unit = {
    instanceStack.pop()
  }

  // Gets the current component instance
  def currentInstance: ComponentInstance = {
    if (instanceStack.nonEmpty) instanceStack.top
    else throw new IllegalStateException("No component is currently rendering.")
  }
}
