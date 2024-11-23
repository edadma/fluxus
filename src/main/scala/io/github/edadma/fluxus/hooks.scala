package io.github.edadma.fluxus

// The useState hook allows components to have state
def useState[T](initialValue: T): (T, T => Unit) = {
  // Get the current component instance
  val instance = RenderContext.currentInstance

  // Capture the current hook index
  val currentHookIndex = instance.hookIndex

  // If this is the first render, initialize the state
  if (instance.hooks.size <= currentHookIndex) {
    instance.hooks += initialValue
  }

  // Get the current state value
  val state = instance.hooks(currentHookIndex).asInstanceOf[T]

  // Increment the hook index for the next hook
  instance.hookIndex += 1

  // Define the setState function to update the state and re-render
  val setState: T => Unit = (newValue: T) => {
    // Update the state in the hooks array
    instance.hooks(currentHookIndex) = newValue

    // Re-render the app starting from the root component
    renderApp()
  }

  (state, setState)
}
