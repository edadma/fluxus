package io.github.edadma.fluxus

def useState[T](initial: T): (T, T => Unit) = {
  val instance = RenderContext.current

  println(s"DEBUG: useState called with initial value: $initial at hookIndex: ${instance.hookIndex}")

  // Add initial state if this hook has not been initialized
  if (instance.hooks.size <= instance.hookIndex) {
    println(s"DEBUG: Adding initial state: $initial")
    instance.addHook(initial)
  }

  // Ensure hookIndex is valid
  if (instance.hookIndex >= instance.hooks.size) {
    throw new IllegalStateException(
      s"Invalid hook access: hookIndex=${instance.hookIndex}, total hooks=${instance.hooks.size}",
    )
  }

  // Capture the current hookIndex
  val currentHookIndex = instance.hookIndex

  // Retrieve state for the current hook index
  val state = instance.hooks(currentHookIndex).asInstanceOf[T]
  println(s"DEBUG: Accessing hook at index: $currentHookIndex, state: $state")

  // Increment hookIndex after successful access
  instance.hookIndex += 1
  println(s"DEBUG: Incremented hookIndex to $currentHookIndex")

  // Define the state updater function
  val setState: T => Unit = (newState: T) => {
    println(s"DEBUG: Updating state at hookIndex=${currentHookIndex} from $state to $newState")
    instance.hooks(currentHookIndex) = newState
    println(s"DEBUG: Current hooks after update: ${instance.hooks.mkString(", ")}")
    render(instance.render, "#app") // Trigger re-render
  }

  (state, setState)
}
