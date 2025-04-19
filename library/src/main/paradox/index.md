# Fluxus

Fluxus is a minimalist UI framework for Scala.js inspired by component-based design patterns like React.

@@@ index

* [Overview](overview.md)
* [Getting Started](getting-started.md)
* [Hooks](hooks/index.md)

@@@

## Introduction

Fluxus provides a modern, type-safe approach to building web applications using Scala.js. It offers a familiar component-based architecture with a virtual DOM implementation for efficient UI updates, combined with the power of Scala's type system.

```scala
import io.github.edadma.fluxus.*

def Counter = () => {
  val (count, setCount, _) = useState(0)
  
  div(
    p(s"Count: $count"),
    button(
      onClick := (() => setCount(count + 1)),
      "Increment"
    )
  )
}

// Render the app to the DOM
render(Counter <> (), "app")
```

## Key Features

- Component-based architecture for building modular UIs
- Efficient virtual DOM reconciliation for optimized updates
- Hook-based state management system
- Powerful event handling for interactive applications
- Ergonomic API inspired by modern web frameworks
- Support for asynchronous data fetching
- Built on Scala.js for type-safe, performant applications

## License

Fluxus is open-source software licensed under the ISC License.