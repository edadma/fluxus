# Overview

Fluxus is a lightweight UI framework for Scala.js that enables you to build component-based web applications using a familiar and ergonomic API. It draws inspiration from modern web frameworks while leveraging the power of Scala's type system.

## Core Concepts

### Components

Components are the building blocks of Fluxus applications. A component is a function that returns a description of what should appear on the screen (a `FluxusNode`). Components can be simple or complex, and they can be composed to build rich UIs.

```scala
def Greeting(props: GreetingProps) = {
  div(
    cls := "greeting",
    h1(s"Hello, ${props.name}!")
  )
}

case class GreetingProps(name: String)
```

Components are rendered using the `<>` operator with appropriate props:

```scala
Greeting <> GreetingProps("World")
```

### Virtual DOM

Fluxus uses a virtual DOM approach to efficiently update the UI. Instead of directly manipulating the DOM, you describe what the UI should look like, and Fluxus takes care of efficiently updating the real DOM to match.

### Hooks

Hooks are functions that let you "hook into" component state and lifecycle features. They provide a way to reuse stateful logic between components without changing your component hierarchy.

```scala
def Counter = () => {
  val (count, setCount, _) = useState(0)
  
  useEffect(() => {
    // This runs after render and when count changes
    println(s"Count is now: $count")
    () // No cleanup needed
  }, Seq(count))
  
  div(
    p(s"Count: $count"),
    button(onClick := (() => setCount(count + 1)), "Increment")
  )
}
```

### Event Handling

Fluxus provides an intuitive way to handle DOM events:

```scala
button(
  onClick := (() => console.log("Button clicked!")),
  "Click me"
)
```

## Framework Design

Fluxus is designed with several key principles in mind:

1. **Type Safety**: Leverage Scala's type system to catch errors at compile time
2. **Performance**: Efficient updates through virtual DOM diffing
3. **Composability**: Build complex UIs from simple, reusable components
4. **Developer Experience**: Provide an ergonomic, intuitive API
5. **Testability**: Support for test-driven development with jsdom

## Comparison with Other Frameworks

Fluxus draws inspiration from several popular frameworks:

- **React**: Component-based architecture, hooks API, virtual DOM
- **Scala.js React**: Type-safe binding to React, but with a more idiomatic Scala approach
- **Laminar**: Reactive approach to UI updates, but with a more familiar component model