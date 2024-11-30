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
  val instance = RenderContext.currentInstance
  FluxusLogger.State.update(
    "useState",
    Map(
      "hookIndex"     -> instance.hookIndex,
      "hooksSize"     -> instance.hooks.size,
      "componentType" -> instance.renderFunction.getClass.getSimpleName,
    ),
  )
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
      FluxusLogger.State.update("setState", Map("needsRender" -> instance.needsRender)) // Add this log
      // Mark all child components for re-render too
      instance.children.foreach(_.needsRender = true)
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
    var cleanup: Option[() => Unit] = None,
    var isActive: Boolean = false, // New field to track if effect is currently running
)

def useEffect(effect: () => Unit | (() => Unit), deps: Seq[Any] = Seq.empty): Unit = {
  val instance         = RenderContext.currentInstance
  val currentHookIndex = instance.hookIndex

  FluxusLogger.State.effect(
    instance.renderFunction.getClass.getSimpleName,
    s"useEffect called: hookIndex=$currentHookIndex, hooksSize=${instance.hooks.size}, depsHash=${deps.hashCode()}",
  )

  if (instance.hooks.size <= currentHookIndex) {
    FluxusLogger.State.effect(
      instance.renderFunction.getClass.getSimpleName,
      "Creating new effect hook and scheduling effect",
    )
    instance.hooks += EffectHook(deps, None)
    instance.effects += (() => {
      FluxusLogger.State.effect(
        instance.renderFunction.getClass.getSimpleName,
        "Running initial effect",
      )
      val cleanup = effect() match {
        case c: (() => Unit) =>
          FluxusLogger.State.effect(
            instance.renderFunction.getClass.getSimpleName,
            "Cleanup function returned",
          )
          Some(c)
        case _ =>
          FluxusLogger.State.effect(
            instance.renderFunction.getClass.getSimpleName,
            "No cleanup needed",
          )
          None
      }
      instance.hooks(currentHookIndex) = EffectHook(deps, cleanup)
    })
  } else {
    val hook = instance.hooks(currentHookIndex).asInstanceOf[EffectHook]
    val depsChanged = deps.length != hook.deps.length ||
      deps.zip(hook.deps).exists { case (a, b) => a != b }

    FluxusLogger.State.effect(
      instance.renderFunction.getClass.getSimpleName,
      s"Checking existing hook: depsChanged=$depsChanged, oldDeps=${hook.deps.hashCode()}, newDeps=${deps.hashCode()}",
    )

    if (depsChanged) {
      FluxusLogger.State.effect(
        instance.renderFunction.getClass.getSimpleName,
        "Dependencies changed - scheduling cleanup and rerun",
      )
      instance.effects += (() => {
        hook.cleanup.foreach(c => {
          FluxusLogger.State.effect(
            instance.renderFunction.getClass.getSimpleName,
            "Running cleanup from previous effect",
          )
          c()
        })
        FluxusLogger.State.effect(
          instance.renderFunction.getClass.getSimpleName,
          "Running new effect after cleanup",
        )
        val cleanup = effect() match {
          case c: (() => Unit) => Some(c)
          case _               => None
        }
        instance.hooks(currentHookIndex) = EffectHook(deps, cleanup)
      })
    }
  }

  instance.hookIndex += 1
}
