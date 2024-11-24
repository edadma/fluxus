/* hooks.scala

This file defines the `useState` hook, which allows components to have stateful logic.
The `useState` function is modeled after React's useState hook, providing a way to manage state within functional components.

Key components:
- `useState` function: Allows components to initialize and manage state variables.
- Integration with `RenderContext` and `ComponentInstance` to ensure state is preserved across renders.

 */

package io.github.edadma.fluxus // Define the package namespace for the Fluxus framework

// The `useState` hook allows components to have state
case class Update[T](func: T => T)

def useState[T](initialValue: T): (T, (T | Update[T]) => Unit) = {
  val instance = RenderContext.currentInstance // Get the current component instance from RenderContext

  val currentHookIndex = instance.hookIndex // Capture the current hook index for this hook

  // If this is the first render or the hook hasn't been initialized, initialize the state
  if (instance.hooks.size <= currentHookIndex) {
    instance.hooks += initialValue // Add the initial value to the hooks array
  }

  val state = instance.hooks(currentHookIndex).asInstanceOf[T] // Retrieve the current state value from the hooks array

  instance.hookIndex += 1 // Increment the hook index for the next hook

  val setState: ((T | Update[T]) => Unit) = (newValueOrFunc: (T | Update[T])) => {
    val newValue = newValueOrFunc match {
      case Update(func: (T => T) @unchecked) =>
        val f = func.asInstanceOf[T => T]
        f(state)
      case value: T @unchecked => value
    }
    instance.hooks(currentHookIndex) = newValue // Update the state in the hooks array with the new value
    renderApp()                                 // Re-render the app starting from the root component
  }

  (state, setState) // Return the current state and the setState function as a tuple
}

// The `useEffect` hook allows side-effects in functional components.
case class EffectHook(
    deps: Seq[Any],
    cleanup: Option[() => Unit],
)

def useEffect(effect: () => Unit | (() => Unit), deps: Seq[Any] = Seq.empty): Unit = {
  val instance         = RenderContext.currentInstance
  val currentHookIndex = instance.hookIndex

  if (instance.hooks.size <= currentHookIndex) {
    // First time, no cleanup
    instance.hooks += EffectHook(deps, None)
    // Schedule the effect to be run after rendering
    instance.effects += (() => {
      val cleanup = effect() match {
        case c: (() => Unit) => Some(c)
        case _               => None
      }
      // Update the hook with the cleanup function
      instance.hooks(currentHookIndex) = EffectHook(deps, cleanup)
    })
  } else {
    val hook = instance.hooks(currentHookIndex).asInstanceOf[EffectHook]
    if (deps != hook.deps) {
      // Deps have changed, run cleanup and effect
      hook.cleanup.foreach(c => c()) // Run cleanup
      // Schedule the new effect
      instance.effects += (() => {
        val cleanup = effect() match {
          case c: (() => Unit) => Some(c)
          case _               => None
        }
        // Update the hook with new deps and cleanup
        instance.hooks(currentHookIndex) = EffectHook(deps, cleanup)
      })
    } else {
      // Deps haven't changed; do nothing
    }
  }

  instance.hookIndex += 1
}
