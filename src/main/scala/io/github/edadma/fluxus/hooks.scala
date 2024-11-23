/* Hooks.scala

This file defines the `useState` hook, which allows components to have stateful logic.
The `useState` function is modeled after React's useState hook, providing a way to manage state within functional components.

Key components:
- `useState` function: Allows components to initialize and manage state variables.
- Integration with `RenderContext` and `ComponentInstance` to ensure state is preserved across renders.

 */

package io.github.edadma.fluxus // Define the package namespace for the Fluxus framework

// The `useState` hook allows components to have state
def useState[T](initialValue: T): (T, T => Unit) = {
  val instance = RenderContext.currentInstance // Get the current component instance from RenderContext

  val currentHookIndex = instance.hookIndex // Capture the current hook index for this hook

  // If this is the first render or the hook hasn't been initialized, initialize the state
  if (instance.hooks.size <= currentHookIndex) {
    instance.hooks += initialValue // Add the initial value to the hooks array
  }

  val state = instance.hooks(currentHookIndex).asInstanceOf[T] // Retrieve the current state value from the hooks array

  instance.hookIndex += 1 // Increment the hook index for the next hook

  val setState: T => Unit = (newValue: T) => {
    instance.hooks(currentHookIndex) = newValue // Update the state in the hooks array with the new value
    renderApp()                                 // Re-render the app starting from the root component
  }

  (state, setState) // Return the current state and the setState function as a tuple
}
