# Hooks

Hooks are functions that let you use state and other Fluxus features in functional components. This section covers the built-in hooks and how to create custom hooks.

@@@ index

* [useState](useState.md)
* [useEffect](useEffect.md)
* [useRef](useRef.md)
* [useMemo and useCallback](useMemo-useCallback.md)
* [useFetch](useFetch.md)
* [useSignal](useSignal.md)
* [Custom Hooks](custom-hooks.md)

@@@

## Overview

Hooks were introduced to solve several problems in component-based UI libraries:

1. **Reusing stateful logic** between components without complex patterns
2. **Splitting components** into smaller functions based on related pieces
3. **Using state** without writing class components

In Fluxus, hooks are implemented using a simple index-based system that tracks hook calls within each component instance.

## Rules of Hooks

To ensure hooks work correctly, you must follow these rules:

1. **Only call hooks at the top level** of your component functions. Don't call hooks inside loops, conditions, or nested functions.

2. **Only call hooks from Fluxus component functions**. Don't call them from regular Scala functions.

The reason for these rules is that Fluxus relies on the order of hook calls to maintain each hook's state across renders.

## Basic Example

Here's a simple example using the `useState` hook:

```scala
val Counter = () => {
  val (count, setCount, _) = useState(0)
  
  div(
    p(s"Count: $count"),
    button(
      onClick := (() => setCount(count + 1)),
      "Increment"
    )
  )
}
```

## Available Hooks

Fluxus provides several built-in hooks:

- **useState**: Adds local state to a function component
- **useEffect**: Performs side effects in function components
- **useRef**: Creates a mutable reference that persists across renders
- **useMemo**: Memoizes expensive computations
- **useCallback**: Memoizes callback functions
- **useFetch**: Fetches data from a server
- **useSignal**: Integrates with Airstream for reactive programming

## Creating Custom Hooks

You can create your own hooks to reuse stateful logic between components. A custom hook is simply a function that calls other hooks.

```scala
def useFormField(initialValue: String): (String, dom.Event => Unit, Boolean) = {
  val (value, setValue, _) = useState(initialValue)
  val (touched, setTouched, _) = useState(false)
  
  val handleChange = (e: dom.Event) => {
    setValue(e.target.asInstanceOf[dom.html.Input].value)
    setTouched(true)
  }
  
  (value, handleChange, touched)
}
```

By convention, custom hook names should start with "use" to make it clear they follow the rules of hooks.

## Next Steps

Explore each hook in detail in the following pages, starting with @ref[useState](useState.md).