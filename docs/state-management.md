# Fluxus State Management Design

## Overview

This document outlines the design for implementing robust state management in Fluxus. The goal is to create a simple but reliable system that can handle any correctly-written application, prioritizing correctness over optimization.

## Core Principles

1. All state updates should be batched to prevent race conditions and ensure consistency
  2. Component instances maintain their own state across re-renders
3. Hooks must follow standard rules (same order every render)
4. Support both direct and functional updates
5. Handle async operations safely

  ## Key Components

### 1. Batch Scheduler

```scala
object BatchScheduler {
  private var isProcessing = false
  private var updates = Queue[StateUpdate]()

  case class StateUpdate(
                          instance: ComponentInstance,
                          hook: StateHook[_],
                          updateFn: Any => Any
                        )

  def scheduleUpdate[T](
                         instance: ComponentInstance,
                         hook: StateHook[T],
                         value: Any
                       ): Unit = {
    updates.enqueue(StateUpdate(instance, hook, _ => value))
    scheduleBatchProcessing()
  }

  def scheduleFunctionalUpdate[T](
                                   instance: ComponentInstance,
                                   hook: StateHook[T],
                                   fn: T => T
                                 ): Unit = {
    updates.enqueue(StateUpdate(instance, hook, fn))
    scheduleBatchProcessing()
  }

  private def scheduleBatchProcessing(): Unit = {
    if (!isProcessing) {
      isProcessing = true
      processBatch()
    }
  }

  private def processBatch(): Unit = {
    // Process all updates in current batch
    val batch = updates
    updates = Queue()

    // Apply all state updates
    batch.foreach { update =>
      val newValue = update.updateFn(update.hook.value)
      update.hook.value = newValue
    }

    // Get unique set of components that need re-rendering
    val componentsToUpdate = batch.map(_.instance).toSet

    // Render each component once
    componentsToUpdate.foreach { instance =>
      renderComponent(instance)
    }

    isProcessing = false

    // Handle any updates that came in during processing
    if (updates.nonEmpty) {
      processBatch()
    }
  }
}
```

### 2. State Hook Implementation

```scala
sealed trait Hook
case class StateHook[T](
                         var value: T,
                         setter: T => Unit
                       ) extends Hook

def useState[T](initial: T): (T, T => Unit) = {
  val instance = ComponentInstance.current.getOrElse(
    throw new Error("Hooks must be called within component render")
  )

  // Get existing or create new hook
  val hook = instance.hooks.lift(instance.hookIndex) match {
    case Some(h: StateHook[T]) => h
    case None =>
      val hook = StateHook(
        value = initial,
        setter = value => BatchScheduler.scheduleUpdate(instance, hook, value)
      )
      instance.hooks = instance.hooks :+ hook
      hook
    case Some(h) =>
      throw new Error(s"Hook mismatch at index ${instance.hookIndex}")
  }

  instance.hookIndex += 1

  // Return current value and setter
  (hook.value, value => {
    value match {
      case f: (T => T) =>
        BatchScheduler.scheduleFunctionalUpdate(instance, hook, f)
      case v =>
        BatchScheduler.scheduleUpdate(instance, hook, v)
    }
  })
}
```

### 3. Component Instance Changes

```scala
case class ComponentInstance(
                              id: String = ComponentInstance.nextId,
                              componentType: String,
                              var hooks: Vector[Hook] = Vector.empty,
                              var hookIndex: Int = 0,
                              var parent: Option[ComponentInstance] = None,
                              var rendered: Option[FluxusNode] = None,
                              var isRendering: Boolean = false
                            )

object ComponentInstance {
  // Existing code remains the same

  def renderComponent(instance: ComponentInstance): Unit = {
    instance.isRendering = true
    instance.hookIndex = 0
    try {
      val newNode = instance.component(instance.props)
      reconcile(instance.rendered, Some(newNode))
      instance.rendered = Some(newNode)
    } finally {
      instance.isRendering = false
    }
  }
}
```

## Key Behaviors

1. State Updates
  - All updates are queued in BatchScheduler
  - Multiple updates to same component are batched
  - Components re-render once per batch

2. Hook Rules
  - Hooks only allowed during render
  - Must be called in same order
  - Maintain state between renders

3. Async Safety
  - Batch processing ensures consistent state
  - New updates during async operations get queued
  - No race conditions between updates

  ## Example Usage

```scala
def Counter(props: Props) = {
  val (count, setCount) = useState(0)
  val (doubled, setDoubled) = useState(0)

  def handleClick() = {
    // Both updates will be batched
    setCount(count + 1)
    setDoubled((count + 1) * 2)  // Uses new count value
  }

  def handleAsyncClick() = {
    setCount(count + 1)
    // Safe even with async code
    fetchData().foreach { data =>
      setCount(c => c + 1)  // Functional update
    }
  }

  div(
    button(onClick := handleClick),
    button(onClick := handleAsyncClick),
    s"Count: $count, Doubled: $doubled"
  )
}
```

## Implementation Steps

1. Add Hook trait and StateHook implementation
  2. Implement BatchScheduler
3. Add hook storage to ComponentInstance
  4. Implement useState hook
  5. Add batch processing to reconciliation
  6. Add error handling and validation
  7. Add tests for all scenarios
