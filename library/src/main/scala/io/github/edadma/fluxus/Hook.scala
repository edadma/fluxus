package io.github.edadma.fluxus

import io.github.edadma.fluxus.core.{BatchScheduler, ComponentInstance}
import org.scalajs.dom

sealed trait Hook
case class StateHook[T](
    var value: T,
    var setter: T => Unit,
    var updater: (T => T) => Unit,
) extends Hook:
  override def toString: String = s"StateHook($value)"

def useState[T](initial: T): (T, T => Unit, (T => T) => Unit) = {
  val instance = ComponentInstance.current.getOrElse(
    throw new Error("Hooks must be called within component render"),
  )

  logger.debug(
    "useState called",
    category = "Hooks",
    Map(
      "instance"      -> instance.id,
      "hookIndex"     -> instance.hookIndex.toString,
      "initialValue"  -> initial.toString,
      "hooks"         -> instance.hooks.toString,
      "totalHooks"    -> instance.hooks.length.toString,
      "isFirstRender" -> (instance.hooks.isEmpty).toString,
    ),
  )

  // During render, we should never try to access a hook index
  // that's beyond what we had in the previous render
  if (instance.hooks.nonEmpty && instance.hookIndex > instance.hooks.length) {
    throw new Error(
      "Hook called conditionally. Hooks must be called in the exact same order on every render.",
    )
  }

  def createHook(): StateHook[T] = {
    val hook = new StateHook[T](
      value = initial,
      setter = null, // Placeholder
      updater = null, // Will be set below
    )

    // Create separate stable functions for direct and functional updates
    val setter = (value: T) => {
      BatchScheduler.scheduleUpdate(instance, hook, value)
    }

    val updater = (fn: T => T) => {
      BatchScheduler.scheduleFunctionalUpdate(instance, hook, fn)
    }

    hook.setter = setter
    hook.updater = updater
    instance.hooks = instance.hooks :+ hook
    hook
  }

  val hook = instance.hooks.lift(instance.hookIndex) match {
    case Some(h: StateHook[_]) =>
      logger.debug(
        "Reusing existing hook",
        category = "Hooks",
        Map(
          "hookIndex"    -> instance.hookIndex.toString,
          "currentValue" -> h.value.toString,
          "allHooks"     -> instance.hooks.toString,
        ),
      )
      h.asInstanceOf[StateHook[T]]
    case Some(_: EffectHook) =>
      throw new Error(s"Hook mismatch: expected StateHook but found EffectHook at index ${instance.hookIndex}")
    case Some(_: MemoHook[_]) =>
      throw new Error(s"Hook mismatch: expected StateHook but found MemoHook at index ${instance.hookIndex}")
    case Some(_: RefHook) =>
      throw new Error(s"Hook mismatch: expected StateHook but found RefHook at index ${instance.hookIndex}")
    case None =>
      logger.debug("Creating new hook", category = "Hooks")

      createHook()
  }

  instance.hookIndex += 1

  logger.debug(
    "Returning hook value",
    category = "Hooks",
    Map(
      "hookIndex"   -> instance.hookIndex.toString,
      "hookValue"   -> hook.value.toString, // Add this
      "returnValue" -> hook.value.toString, // Add this
    ),
  )

  (hook.value, hook.setter, hook.updater)
}

case class EffectHook(
    var effect: () => (() => Unit) | Unit, // Effect fn returning optional cleanup
    var deps: Seq[Any],                    // Dependencies (null means run every time)
    var cleanup: Option[() => Unit],       // Last cleanup function if any
    var lastDeps: Seq[Any],                // Previous deps for comparison
    var hasRun: Boolean = false,
) extends Hook:
  override def toString: String = "EffectHook"

def useEffect(effect: () => (() => Unit) | Unit, deps: Seq[Any] = null): Unit = {
  val instance = ComponentInstance.current.getOrElse(
    throw new Error("Hooks must be called within component render"),
  )

  logger.debug(
    "useEffect called",
    category = "Hooks",
    Map(
      "instance"  -> instance.id,
      "hookIndex" -> instance.hookIndex.toString,
      "hasDeps"   -> (deps != null).toString,
      "deps"      -> Option(deps).map(_.mkString(", ")).getOrElse("null"),
    ),
  )

  // Get or create hook
  val hook = instance.hooks.lift(instance.hookIndex) match {
    case Some(h: EffectHook) =>
      logger.debug(
        "Reusing existing effect hook",
        category = "Hooks",
        Map(
          "hasCleanup" -> h.cleanup.isDefined.toString,
          "lastDeps"   -> Option(h.lastDeps).map(_.mkString(", ")).getOrElse("null"),
        ),
      )
      h
    case None =>
      logger.debug("Creating new effect hook", category = "Hooks")
      val hook = EffectHook(effect, deps, None, deps)
      instance.hooks = instance.hooks :+ hook
      hook
    case Some(h) =>
      throw new Error(
        s"Hook mismatch at index ${instance.hookIndex}: expected EffectHook but found ${h.getClass.getSimpleName}",
      )
  }

  // Update effect function and dependencies
  hook.effect = effect
  hook.deps = deps

  instance.hookIndex += 1
}

case class MemoHook[T](
    var value: T,
    var deps: Seq[Any],
) extends Hook

def useMemo[T](compute: () => T, deps: Seq[Any]): T = {
  val instance = ComponentInstance.current.getOrElse(
    throw new Error("Hooks must be called within component render"),
  )

  logger.debug(
    "useMemo called",
    category = "Hooks",
    Map(
      "instance"  -> instance.id,
      "hookIndex" -> instance.hookIndex.toString,
      "hasDeps"   -> (deps != null).toString,
      "deps"      -> Option(deps).map(_.mkString(", ")).getOrElse("null"),
    ),
  )

  val hook = instance.hooks.lift(instance.hookIndex) match {
    case Some(h: MemoHook[_]) =>
      logger.debug(
        "Reusing existing memo hook",
        category = "Hooks",
        Map(
          "lastDeps" -> Option(h.deps).map(_.mkString(", ")).getOrElse("null"),
        ),
      )
      val hook = h.asInstanceOf[MemoHook[T]]

      // Check if dependencies have changed
      val shouldRecompute = deps == null ||
        hook.deps == null ||
        deps.length != hook.deps.length ||
        deps.zip(hook.deps).exists { case (a, b) => a != b }

      if (shouldRecompute) {
        logger.debug(
          "Dependencies changed, recomputing value",
          category = "Hooks",
          Map(
            "oldDeps" -> Option(hook.deps).map(_.mkString(", ")).getOrElse("null"),
            "newDeps" -> Option(deps).map(_.mkString(", ")).getOrElse("null"),
          ),
        )
        hook.value = compute()
        hook.deps = deps
      }

      hook
    case None =>
      logger.debug("Creating new memo hook", category = "Hooks")
      val newHook = MemoHook(compute(), deps)
      instance.hooks = instance.hooks :+ newHook
      newHook
    case Some(h) =>
      throw new Error(
        s"Hook mismatch at index ${instance.hookIndex}: expected MemoHook but found ${h.getClass.getSimpleName}",
      )
  }

  instance.hookIndex += 1
  hook.value
}

// Define RefObject as a trait to allow for different implementations
trait RefHook extends Hook {
  type RefType
  var current: RefType
}

/** Creates a mutable ref object that persists for the lifetime of the component.
  *
  * @param initialValue
  *   The initial value for the ref (defaults to null)
  * @return
  *   A RefObject with a mutable .current property
  */
def useRef[T](initialValue: T = null.asInstanceOf[T]): RefHook & { type RefType = T } = {
  val instance = ComponentInstance.current.getOrElse(
    throw new Error("Hooks must be called within component render"),
  )

  logger.debug(
    "useRef called",
    category = "Hooks",
    Map(
      "instance"     -> instance.id,
      "hookIndex"    -> instance.hookIndex.toString,
      "hooksLength"  -> instance.hooks.length.toString,
      "initialValue" -> Option(initialValue).map(_.toString).getOrElse("null"),
    ),
  )

  // Make sure check matches the one in useState
  if (instance.hooks.nonEmpty && instance.hookIndex > instance.hooks.length) {
    throw new Error(
      "Hook called conditionally. Hooks must be called in the exact same order on every render.",
    )
  }

  // Create or reuse ref hook
  val hook = instance.hooks.lift(instance.hookIndex) match {
    case Some(h: RefHook) =>
      logger.debug(
        "Reusing existing ref hook",
        category = "Hooks",
        Map(
          "hookIndex"  -> instance.hookIndex.toString,
          "refCurrent" -> Option(h.current).map(_.toString).getOrElse("null"),
        ),
      )
      h.asInstanceOf[RefHook & { type RefType = T }]

    case Some(h) =>
      logger.error(
        "Hook mismatch",
        category = "Hooks",
        Map(
          "hookIndex"    -> instance.hookIndex.toString,
          "foundType"    -> h.getClass.getSimpleName,
          "expectedType" -> "RefHook",
        ),
      )
      throw new Error(
        s"Hook mismatch: expected RefHook but found ${h.getClass.getSimpleName} at index ${instance.hookIndex}",
      )

    case None =>
      logger.debug(
        "Creating new ref hook",
        category = "Hooks",
        Map("hookIndex" -> instance.hookIndex.toString),
      )
      // Create a generic ref hook for any type
      val newHook = new RefHook {
        type RefType = T
        var current: T = initialValue

        override def toString: String = s"RefHook($current)"
      }
      instance.hooks = instance.hooks :+ newHook
      newHook
  }

  instance.hookIndex += 1
  hook
}

// Function to forward a ref to an element node
def forwardRef[P <: Product](render: (P, RefHook) => FluxusNode): (P, RefHook) => FluxusNode = render
