/* RenderContext.scala

This file defines the RenderContext object, which is a critical component of the rendering process in your simple Scala.js app framework.
It maintains the necessary context during the rendering of components, especially for managing state and hooks in nested components.

The context includes:
- A stack of ComponentInstance objects to keep track of the component hierarchy during rendering.
- A component ID counter to assign unique identifiers to components during each render cycle.

 */

package io.github.edadma.fluxus

object RenderContext {
  /* A stack to keep track of the ComponentInstance objects for components currently being rendered.
     This stack allows nested components to access their own ComponentInstance during the rendering process.
     When rendering a component, its instance is pushed onto the stack.
     When the component's rendering is complete, its instance is popped off the stack.
   */
  private val instanceStack = new scala.collection.mutable.Stack[ComponentInstance]()

  /* A counter used to assign unique IDs to components during each render cycle.
     This counter is reset to zero at the beginning of each render (in the renderApp function).
     Assigning consistent IDs across renders (as long as the component tree structure doesn't change)
     is essential for preserving component state across renders.
   */
  var componentIdCounter: Int = 0

  /* Pushes a ComponentInstance onto the instanceStack.
     This method is called when a component begins rendering.

     Parameters:
     - instance: The ComponentInstance of the component that is about to render.
   */
  def push(instance: ComponentInstance): Unit = {
    instanceStack.push(instance)
    /*
      - Adds the component instance to the top of the stack.
      - This ensures that the current rendering context includes this component.
      - Nested components will have their instances pushed onto the stack in turn.
     */
  }

  /* Pops the most recently pushed ComponentInstance off the instanceStack.
     This method is called when a component has finished rendering.
     It removes the current component's instance from the stack, allowing the rendering context to return to the parent component.
   */
  def pop(): Unit = {
    instanceStack.pop()
    /*
      - Removes the top component instance from the stack.
      - Restores the rendering context to the parent component.
      - Ensures that any subsequent operations are performed in the correct context.
     */
  }

  /* Retrieves the current ComponentInstance from the top of the instanceStack.
     This method is essential for hooks like useState, which need to access the current component's instance
     to store and retrieve state associated with that component.

     Returns:
     - The ComponentInstance at the top of the instanceStack (i.e., the component currently being rendered).

     Throws:
     - IllegalStateException if the instanceStack is empty, which indicates that no component is currently being rendered.
   */
  def currentInstance: ComponentInstance = {
    if (instanceStack.nonEmpty)
      instanceStack.top
    else
      throw new IllegalStateException("No component is currently rendering.")
    /*
      - Checks if there is a component instance on the stack.
      - If so, returns the top component instance.
      - If not, throws an exception to indicate improper usage.
     */
  }
}
