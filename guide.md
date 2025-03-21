# Fluxus Library Guide - Internal Reference Document

## Overview

Fluxus is a minimalist UI framework built with Scala.js, inspired by component-based design patterns (specifically React). It provides a type-safe way to build web applications using Scala with a virtual DOM implementation, hooks-based state management, and other modern frontend features.

## Core Concepts

### Project Structure

- **Core Layer**: Low-level implementation of the virtual DOM, diffing algorithm, component lifecycle management, and DOM operations (`./library/src/main/scala/io/github/edadma/fluxus/core`)
- **API Layer**: User-facing ergonomic APIs for component creation, hooks, and utilities (`./library/src/main/scala/io/github/edadma/fluxus`)
- **Examples**: Sample applications showcasing library usage (`./examples/src/main/scala/io/github/edadma/fluxus/examples`)
- **Tests**: Comprehensive test suite using jsdom for DOM simulation (`./library/src/test/scala/io/github/edadma/fluxus/testing`)

### Key Components

1. **Component Model**:
    - Components are functions that take props and return `FluxusNode`
    - Support for stateless and stateful components
    - Component instances maintain state and lifecycle

2. **Virtual DOM**:
    - `FluxusNode` hierarchy (ElementNode, TextNode, ComponentNode, RawNode)
    - Efficient diffing algorithm to minimize DOM operations
    - Keyed reconciliation for efficient list rendering

3. **State Management**:
    - Hook-based state system (`useState`, `useEffect`)
    - Batched state updates for optimal performance
    - Signal-based shared state with Airstream integration

4. **Event Handling**:
    - DOM event binding with type-safe handlers
    - Support for synthetic events

5. **Data Fetching**:
    - `useFetch` hook for declarative data fetching
    - Support for loading, error, and success states
    - Built-in retry logic

6. **Testing**:
    - jsdom-based DOM simulation
    - AsyncDOMSpec for asynchronous testing
    - MockServer for backend API simulation

## API Reference

### Creating Elements

```scala
// Element creation with attributes and children
div(
  cls := "container",
  onClick := (() => handleClick()),
  h1("Hello World"),
  p("This is a paragraph")
)

// Common HTML elements have helper functions
div(), span(), h1(), p(), button(), input(), etc.

// SVG elements
svg(), circle(), rect(), path(), etc.
```

### Components

```scala
// Defining a component with props
case class GreetingProps(name: String)

def Greeting(props: GreetingProps): FluxusNode = {
  div(s"Hello, ${props.name}!")
}

// Using a component
Greeting <> GreetingProps("World")

// Component without props
def SimpleComponent = () => div("Simple component")
SimpleComponent <> ()
```

### Hooks

```scala
// State hook
val (count, setCount, updateCount) = useState(0)
// setCount(5) // Direct update
// updateCount(_ + 1) // Functional update

// Effect hook
useEffect(
  () => {
    // Effect code
    // Optional cleanup function
    () => { /* cleanup */ }
  },
  Seq(dependency1, dependency2) // Dependencies
)

// Signal hook for shared state
val counter = useSignal(counterSignal)
```

### Fetch and Data

```scala
// Basic fetch
val (state, retry) = useFetch[List[User]](
  url = "/api/users",
  dependencies = Seq(dependency1),
  options = FetchOptions(
    method = "GET",
    headers = Map("Content-Type" -> "application/json"),
    retries = 3
  )
)

// Handling different states
state match {
  case FetchState.Idle() => // Not started
  case FetchState.Loading() => // In progress
  case FetchState.Success(data) => // Success with data
  case FetchState.Error(error) => // Error state
}
```

### Conditional Rendering

```scala
// Using if expressions
div(
  if showHeader then
    h1("Header")
  else
    null,
  p("Always visible")
)

// Using Option
div(
  Option.when(showDetails)(
    div("Details here...")
  )
)
```

### List Rendering

```scala
// Mapping collections to nodes
ul(
  items.map(item => 
    li(key := item.id, item.name)
  )
)
```

### Event Handling

```scala
// Click handler
button(
  onClick := (() => handleClick())
)

// Input change
input(
  value := text,
  onInput := ((e: dom.Event) => 
    setText(e.target.asInstanceOf[dom.html.Input].value)
  )
)

// Form submission
form(
  onSubmit := ((e: dom.Event) => {
    e.preventDefault()
    handleSubmit()
  })
)
```

## Rendering to DOM

```scala
// Render a component to the DOM
render(App, "app") // Renders to element with id="app"
render(App, container) // Renders to a specific DOM element
```

## Testing Utilities

### DOM Testing

```scala
// Creating a test component
val node = TestComponent <> TestProps()
createDOM(node, container)

// Checking DOM content
container.textContent shouldBe "Expected text"
container.querySelector(".class-name") should not be null

// Simulating events
click(container.querySelector("button"))
typeInput(container.querySelector("input"), "Test value")
```

### Async Testing

```scala
// Testing asynchronous behavior
eventually {
  container.textContent shouldBe "Updated text"
}

// Testing multiple updates
eventually {
  // First assertion
}.flatMap { _ =>
  // Trigger another update
  click(container.querySelector("button"))
  
  eventually {
    // Next assertion
  }
}
```

### Mock Server

```scala
// Creating a mock server
val mockServer = MockServer(
  MockEndpoint(
    path = "/api/users/:id",
    method = "GET",
    handler = req => {
      new MockResponse()
        .status(200)
        .json(User(id = req.params("id").toInt, name = "Test User"))
    }
  )
)

// Override fetch for tests
mockServer.overrideFetch()
```

## Key Implementation Details

### BatchScheduler

The `BatchScheduler` is responsible for batching state updates to prevent unnecessary re-renders. It collects updates and processes them in the next microtask to ensure consistency.

### Component Instance

Each component gets a `ComponentInstance` that tracks:
- Hooks and their state
- Rendered output
- Parent-child relationships
- Lifecycle methods

### Virtual DOM Diffing

The diffing algorithm compares old and new virtual DOM trees to generate minimal DOM operations:
- Text updates
- Attribute changes
- Node insertions/deletions
- Node moves (for keyed lists)

### Hook Implementation

Hooks are tied to component instances and rely on call order consistency (like React). The system tracks:
- Current hook index during rendering
- Previous hook values for comparison
- Dependencies for conditional re-execution

## Common Patterns

### Form Handling

```scala
def FormComponent = () => {
  val (formData, setFormData, updateFormData) = useState(Map[String, String]())
  
  def handleChange(e: dom.Event) = {
    val input = e.target.asInstanceOf[dom.html.Input]
    updateFormData(_ + (input.name -> input.value))
  }
  
  def handleSubmit(e: dom.Event) = {
    e.preventDefault()
    // Process form data
  }
  
  form(
    onSubmit := (handleSubmit(_)),
    input(
      name := "username",
      value := formData.getOrElse("username", ""),
      onInput := (handleChange(_))
    ),
    button(typ := "submit", "Submit")
  )
}
```

### Data Loading

```scala
def DataComponent = () => {
  val (data, retry) = useFetch[List[Item]]("/api/items")
  
  div(
    data match {
      case FetchState.Loading() => div("Loading...")
      case FetchState.Success(items) => 
        ul(items.map(item => li(key := item.id, item.name)))
      case FetchState.Error(error) => 
        div(s"Error: ${error.getMessage}", button(onClick := (() => retry()), "Retry"))
      case FetchState.Idle() => div("Idle")
    }
  )
}
```

### Derived State

```scala
def DerivedStateComponent = () => {
  val (items, setItems, _) = useState(List[Item]())
  
  // Derived state
  val totalPrice = items.map(_.price).sum
  val itemCount = items.size
  
  div(
    p(s"Total items: $itemCount"),
    p(s"Total price: $totalPrice")
  )
}
```

### Custom Hooks

```scala
// Define custom hook
def useWindowSize(): (Int, Int) = {
  val (width, setWidth, _) = useState(dom.window.innerWidth)
  val (height, setHeight, _) = useState(dom.window.innerHeight)
  
  useEffect(
    () => {
      val handler = (_: dom.Event) => {
        setWidth(dom.window.innerWidth)
        setHeight(dom.window.innerHeight)
      }
      
      dom.window.addEventListener("resize", handler)
      
      () => dom.window.removeEventListener("resize", handler)
    },
    Seq()
  )
  
  (width, height)
}

// Use custom hook
def ResponsiveComponent = () => {
  val (width, height) = useWindowSize()
  
  div(s"Window size: $width x $height")
}
```

## Gotchas and Best Practices

1. **Hook Rules**:
    - Always call hooks at the top level, never in conditionals
    - Maintain consistent hook order between renders
    - Never call hooks outside of component functions

2. **Component Props**:
    - Use case classes for props
    - Add `key` property for list items
    - Consider making props immutable

3. **Performance**:
    - Use keys for list items
    - Memoize expensive computations outside render functions
    - Avoid creating new functions in render path
    - Keep render functions pure

4. **Event Handling**:
    - Remember DOM events use camelCase (onClick, onInput, etc.)
    - For input elements, use `onInput` instead of `onChange` for immediate updates
    - Prevent default browser behavior with `e.preventDefault()`

5. **Testing**:
    - Use `eventually` for asynchronous assertions
    - Mock API responses for deterministic tests
    - Isolate tests with proper cleanup

6. **Error Handling**:
    - Handle fetch errors gracefully
    - Provide retry mechanisms
    - Use try/catch for error boundaries

7. **Component Structure**:
    - Keep components small and focused
    - Extract reusable logic into custom hooks
    - Follow a clear component hierarchy

## Dependencies and Integration

- **zio-json**: For JSON encoding/decoding in fetch operations
- **Airstream**: For signal-based state management
- **logger**: For internal debugging and logging
- **scalajs-dom**: For DOM API access
- **jsdom**: For testing DOM operations

## Development Notes

- The library uses a test-driven development (TDD) approach with comprehensive test coverage
- DOM operations are batched for performance
- Component lifecycle follows React-like patterns (mount, update, unmount)
- Event delegation could be implemented for better performance in the future
- Signal integration provides a way to share state across components
