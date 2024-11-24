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

case class StateHook[T](var state: T, setState: (T | Update[T]) => Unit)

def useState[T](initialValue: T): (T, (T | Update[T]) => Unit) = {
  val instance         = RenderContext.currentInstance
  val currentHookIndex = instance.hookIndex

  if (instance.hooks.size <= currentHookIndex) {
    var stateHook: StateHook[T] = null
    val setState: (T | Update[T]) => Unit = newValueOrFunc => {
      FluxusLogger.State.update(instance.renderFunction.getClass.getSimpleName, newValueOrFunc)
      val newValue = newValueOrFunc match {
        case Update(func: (T => T) @unchecked) => func(stateHook.state)
        case value: T @unchecked               => value
      }
      stateHook.state = newValue
      instance.needsRender = true
      renderApp()
    }
    stateHook = StateHook(initialValue, setState)
    instance.hooks += stateHook
  }

  val stateHook = instance.hooks(currentHookIndex).asInstanceOf[StateHook[T]]
  instance.hookIndex += 1

  (stateHook.state, stateHook.setState)
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
    val depsChanged = deps.length != hook.deps.length ||
      deps.zip(hook.deps).exists { case (a, b) => a != b }

    if (depsChanged) {
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
