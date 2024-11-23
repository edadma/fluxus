package io.github.edadma.fluxus

def useState[T](initial: T): (T, T => Unit) = {
  val instance = RenderContext.current

  // Add a new hook for initial render
  if (instance.hooks.size <= instance.hookIndex) {
    instance.addHook(initial)
  }

  // Retrieve current state
  val state = instance.nextHook[T]

  // Define the updater function
  val setState: T => Unit = (newState: T) => {
    instance.hooks(instance.hookIndex - 1) = newState
    render(instance.render(), "#app") // Simplified rerender
  }

  (state, setState)
}
