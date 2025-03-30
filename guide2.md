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

## Component Declaration and Usage

### Component Patterns

Fluxus has strict requirements for how components must be declared and used to ensure proper reconciliation and state management. There are two main patterns depending on whether a component needs props:

#### 1. Components with Props

Components that require props must be defined as functions that accept a props parameter and return a `FluxusNode`:

```scala
// Define a case class for props
case class GreetingProps(name: String)

// CORRECT: Define component as a function that takes props
def Greeting = (props: GreetingProps) => {
  div(s"Hello, ${props.name}!")
}

// Usage:
Greeting <> GreetingProps("World")
```

#### 2. Components without Props

Components that don't need props must be defined as functions that accept an empty tuple and return a `FluxusNode`:

```scala
// CORRECT: Define no-props component as a function that takes unit
def SimpleComponent = () => {
  div("Simple component")
}

// Usage:
SimpleComponent <> ()
```

#### 3. Application Entry Point Exception

The main application entry point component is the only exception to these patterns. For convenience, it can be defined as a method that returns a `FluxusNode`:

```scala
// CORRECT (only for app entry point): Define as a method returning FluxusNode
def App: FluxusNode = {
  div(
    h1("Hello Fluxus!"),
    SimpleComponent <> (),
    Greeting <> GreetingProps("User")
  )
}

// Initial rendering:
render(App, "app") // Renders to element with id="app"
```

### Important: Incorrect Component Patterns to Avoid

The following patterns will not work correctly with Fluxus's reconciliation engine:

```scala
// INCORRECT: Defining non-entry components as methods
def WrongComponent(): FluxusNode = {
  div("This won't work properly")
}

// INCORRECT: Using a method call instead of <> operator
div(
  WrongComponent() // This bypasses the reconciliation process
)

// INCORRECT: Missing the unit parameter for no-props components
def AnotherWrongComponent = {
  div("This won't work properly")
}
```

### Why These Patterns Matter

The Fluxus reconciliation engine relies on specific component declaration patterns to:

1. **Track Component Identity**: The function reference is used to identify component types
2. **Maintain Component State**: Hooks like `useState` and `useEffect` are tied to component instances
3. **Optimize Rendering**: The diffing algorithm compares previous and current renders
4. **Manage Component Lifecycle**: Mounting, updating, and unmounting events are triggered correctly

Using incorrect patterns might lead to:
- State reset between renders
- Effects running more often than expected
- Performance degradation
- Unexpected UI behavior

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

def Greeting = (props: GreetingProps) => {
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

#### useSignal Hook

The `useSignal` hook integrates with Airstream's reactive state management system to allow components to subscribe to external reactive state.

**Creating Signals with Airstream**:

```scala
import com.raquo.airstream.state.Var

// Create a Var (mutable signal)
val counterSignal = Var[Int](0) // Initial value 0

// Create a read-only signal that's derived from another signal
val doubledSignal = counterSignal.signal.map(_ * 2)
```

**Using Signals in Components**:

```scala
// In component - subscribes to the signal and re-renders when it changes
val count = useSignal(counterSignal)

// Using the value in render - it's just a regular value
div(
  cls := "counter",
  s"Current count: $count"
)

// Updating the signal from event handlers
button(
  onClick := (() => {
    // Update the signal - this will trigger re-render of all components using this signal
    Transaction { _ =>
      counterSignal.set(count + 1)
    }
  }),
  "Increment"
)
```

**Implementation Details**:
- `useSignal` creates a subscription to the Airstream signal within an effect
- When the signal value changes, it updates the component's state
- The subscription is automatically cleaned up when the component unmounts
- Uses an `Owner` internally to manage subscription lifecycle

```scala
// Internal implementation (simplified)
def useSignal[A](signal: Signal[A]): A = {
  val (value, setValue, _) = useState[A](signal.now())

  useEffect(
    () => {
      val owner = new Owner { def killSubscriptions(): Unit = {} }
      val observer = Observer[A](setValue)
      val subscription = signal.addObserver(observer)(owner)
      
      // Cleanup on unmount
      () => subscription.kill()
    },
    Seq(signal) // Recreate subscription if signal reference changes
  )

  value
}
```

**Updating Signals**:

Signals should be updated using the Airstream Transaction API to ensure consistency:

```scala
// Import Transaction
import com.raquo.airstream.core.Transaction

// In a component or event handler
Transaction { _ =>
  counterSignal.set(10)
  // Additional signal updates in the same transaction
  otherSignal.set("new value")
}

// Functional updates
Transaction { _ =>
  counterSignal.update(_ + 1)
}
```

**Use Cases**:
1. **Global State Management**: Sharing state between unrelated components
2. **Real-time Updates**: Integrating with websockets or other event streams
3. **Form Management**: Complex form state that needs to be accessed from multiple components
4. **Application State**: Centralized store for application state

**Example: Shared Counter Across Components**:

```scala
// Define a shared signal at module level
val counterSignal = Var[Int](0)

// Component A - displays and increments counter
def CounterDisplay = () => {
  val count = useSignal(counterSignal)
  
  div(
    p(s"Count: $count"),
    button(
      onClick := (() => Transaction(_ => counterSignal.update(_ + 1))),
      "Increment"
    )
  )
}

// Component B - also displays counter and can reset it
def CounterReset = () => {
  val count = useSignal(counterSignal)
  
  div(
    p(s"Current value: $count"),
    button(
      onClick := (() => Transaction(_ => counterSignal.set(0))),
      "Reset"
    )
  )
}

// Parent component using both
def App = () => {
  div(
    h1("Shared Counter Demo"),
    CounterDisplay <> (),
    CounterReset <> ()
  )
}
```

**Best Practices**:
- Initialize signals outside components for sharing
- Use transactions when updating multiple signals together
- Keep signal updates logic together with signal definitions when possible
- Consider using a "store" pattern for organizing related signals

#### useRef Hook

The `useRef` hook creates a mutable reference that persists between renders. It provides a way to access DOM elements directly and store mutable values without triggering re-renders.

```scala
// Create a ref with optional initial value
val inputRef = useRef[dom.html.Input]() // Type parameter specifies the expected type
val countRef = useRef[Int](0) // Can store any value type with optional initial value

// Accessing the current value
val currentValue = countRef.current

// Mutating the value (doesn't trigger a re-render)
countRef.current = 5

// Attaching ref to a DOM element
input(
  ref := inputRef, // Special syntax for DOM refs
  // other props...
)

// Accessing DOM element methods
button(
  onClick := (() => {
    inputRef.current.focus() // Direct DOM manipulation
    inputRef.current.value = "New value" // Changing input value imperatively
  })
)
```

**Implementation Details**:
- `useRef` creates a `RefHook` object that has a mutable `current` property
- Unlike React, `useRef` in Fluxus uses Scala's type system to enforce type safety for the ref value
- The `current` property is typed using a path-dependent type (`RefType`) which ensures type safety
- The ref is tied to the component instance and persists across renders
- A special `ref` attribute is recognized by the virtual DOM system to attach DOM elements

**Use Cases**:
1. **DOM Access**: Getting direct access to DOM elements for imperative operations
2. **Storing Instance Values**: Keeping values that don't affect rendering
3. **Preserving Values Between Renders**: When you need to remember a value without causing re-renders
4. **Measuring DOM Elements**: Getting element dimensions or positions
5. **Managing Focus**: Focusing form elements programmatically
6. **Integrating with Third-party Libraries**: Managing non-React DOM libraries

**Ref Forwarding**:
Refs can be forwarded through components using `forwardRef`:

```scala
// Define a component that receives a ref from its parent
val CustomInput = forwardRef[InputProps] { (props, ref) =>
  input(
    typ := "text",
    placeholder := props.placeholder,
    ref := ref.asInstanceOf[RefHook & { type RefType <: dom.html.Input }]
  )
}

// In parent component
def ParentComponent = () => {
  val inputRef = useRef[dom.html.Input]()
  
  div(
    CustomInput <> (InputProps("Enter text..."), inputRef),
    button(
      onClick := (() => inputRef.current.focus()),
      "Focus Input"
    )
  )
}
```

**Internal Working**:
- `useRef` follows the same hook rules as other hooks (must be called at the top level, etc.)
- The ref object is created once during the first render and the same object is returned on subsequent renders
- The ref object's identity is stable across renders
- Refs are stored in the component instance's hooks array
- When a ref is attached to a DOM element, the element is assigned to the `current` property during the render phase

**Key Differences from React**:
- Type-safe refs with Scala's type system
- Path-dependent types ensure correct DOM element type
- More explicit casting required in some cases due to Scala's type system

#### useMemo Hook

`useMemo` is a hook for memoizing expensive computations so they're only re-executed when dependencies change.

```scala
// Basic usage
val memoizedValue = useMemo(
  () => computeExpensiveValue(dependency1, dependency2),
  Seq(dependency1, dependency2) // Dependencies array
)

// Example: Memoizing a complex calculation
val sortedItems = useMemo(
  () => {
    items.sortWith((a, b) => a.priority > b.priority)
  },
  Seq(items) // Only recalculate when items change
)

// Example: Memoizing an expensive object creation
val memoizedObject = useMemo(
  () => {
    Map(
      "processedData" -> processData(data),
      "statistics" -> calculateStats(data)
    )
  },
  Seq(data) // Only recalculate when data changes
)
```

**Implementation Details**:
- `useMemo` creates a `MemoHook` that caches both the computed value and dependencies
- On initial render, it computes the value and stores it with the dependencies
- On subsequent renders, it compares the new dependencies with the previous ones
- If dependencies haven't changed, it returns the cached value without recomputing
- If dependencies have changed, it recomputes the value, caches it, and returns the new value
- Dependencies are compared using reference equality (`==`)

**Dependency Behaviors**:
- `null` for dependencies: Recompute on every render (no memoization)
- Empty sequence `Seq()`: Compute once on mount, never recompute
- Dependencies with values: Recompute only when dependencies change

**Use Cases**:
1. **Expensive Calculations**: Memoizing costly computations
2. **Derived Data**: Computing derived state from props or other state
3. **Preventing Unnecessary Renders**: Optimizing performance by reducing work
4. **Referential Equality**: Maintaining stable references to objects or functions
5. **Data Transformations**: Filtering, sorting, or processing collections

**Internal Working**:
- `useMemo` follows the same hook rules as other hooks
- The memoization cache is tied to the component instance
- Dependencies are shallow compared using `==`
- The cache consists of the last computed value and a copy of the dependencies
- Recomputation happens during render when dependencies change

**Gotchas and Best Practices**:
- Don't put side effects in `useMemo`; use `useEffect` instead
- Keep dependency arrays accurate and complete
- Don't create new objects or arrays inside the component that are used as dependencies
- Beware of over-optimization; only memoize genuinely expensive operations
- Remember that memoization itself has a cost

#### useCallback Hook

`useCallback` is a specialized version of `useMemo` specifically for memoizing function references to prevent unnecessary re-renders.

```scala
// Basic usage
val memoizedCallback = useCallback(
  (event: dom.Event) => {
    console.log(s"Clicked with ${dependency}")
    handleAction(dependency)
  },
  Seq(dependency) // Dependencies array
)

// Example: Stable event handler that depends on state
val handleIncrement = useCallback(
  () => setCount(count + step),
  Seq(count, step) // Only recreate when count or step changes
)

// Example: Passing stable callbacks to child components
val handleItemClick = useCallback(
  (id: String) => {
    setSelectedId(id)
    loadDetails(id)
  },
  Seq(setSelectedId, loadDetails) // Only recreate if these functions change
)
```

**Implementation Details**:
- `useCallback` is implemented using `useMemo` internally
- It memoizes the function reference rather than the function's result
- It returns the same function reference if dependencies haven't changed
- It creates a new function reference only when dependencies change

```scala
// Internal implementation (simplified)
def useCallback[T](callback: T, deps: Seq[Any]): T = 
  useMemo(() => callback, deps)
```

**Use Cases**:
1. **Event Handlers**: Stabilizing event handler references for child components
2. **Callback Props**: Passing stable callback references to child components
3. **Dependencies in useEffect**: When a function is used in an effect's dependency array
4. **Avoiding Unnecessary Renders**: Preventing children from re-rendering due to function reference changes
5. **Hook Dependencies**: When a function is used as a dependency in another hook

**Key Benefits**:
- **Performance Optimization**: Prevents unnecessary re-renders of child components
- **Referential Stability**: Maintains stable function references between renders
- **Dependency Chain Optimization**: Helps stabilize dependency arrays in other hooks

**Internal Working**:
- Functions in Scala.js are objects that get recreated each render
- `useCallback` preserves the same function reference across renders
- It follows the same dependency comparison logic as `useMemo`
- The memoized function maintains its closure over the captured variables

**Gotchas and Best Practices**:
- Always include all values from the component scope that are used inside the callback
- Don't overuse `useCallback` for functions that aren't passed to child components
- Remember that the function's closure captures variables at the time it was created
- For functions that only use stable values (like setState), you can use an empty dependency array
- Be aware of the tradeoff: memoization has its own performance cost

**Example: Combined with Other Hooks**:
```scala
def SearchComponent = () => {
  val (query, setQuery, _) = useState("")
  val (results, setResults, _) = useState(List[Result]())
  
  // Stable search function that depends on query
  val handleSearch = useCallback(
    () => {
      fetchResults(query).foreach { newResults =>
        setResults(newResults)
      }
    },
    Seq(query) // Only recreate when query changes
  )
  
  // Effect uses the stable callback
  useEffect(
    () => {
      if (query.length >= 3) {
        handleSearch()
      }
    },
    Seq(handleSearch) // Depends on the memoized callback
  )
  
  div(
    input(
      value := query,
      onInput := ((e: dom.Event) => setQuery(e.target.asInstanceOf[dom.html.Input].value))
    ),
    button(onClick := (() => handleSearch()), "Search"),
    ResultsList <> ResultsListProps(results, handleItemClick)
  )
}
```

**Performance Considerations**:
- For simple components that don't have expensive child renders, `useCallback` might not provide significant benefits
- The memoization itself has a cost (comparing dependencies, storing references)
- It's most valuable when:
    - Passing callbacks to heavy child components that use memoization
    - Working with complex dependency chains
    - Integrating with code that expects stable references

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

#### Signal Hook Implementation

The `useSignal` hook is implemented using internal subscription mechanisms:

```scala
// Simplified implementation
private class EffectOwner extends Owner {
  def cleanup(): Unit = killSubscriptions()
}

def useSignal[A](signal: Var[A]): A = {
  // Initialize Airstream's transaction system
  Transaction { _ => () } 

  // Local state to store the signal value
  val (value, setValue, _) = useState[A](signal.now())

  // Subscribe to signal changes via effect
  useEffect(
    () => {
      // Create owner for subscription management
      val owner = new EffectOwner()
      // Create observer that updates state
      val observer = Observer[A](setValue)

      // Subscribe to the signal
      val subscription = signal.signal.addObserver(observer)(owner)

      // Cleanup on unmount or signal change
      () => {
        Transaction { _ =>
          subscription.kill()
          owner.cleanup()
        }
      }
    },
    Seq(signal) // Depend on signal reference
  )

  // Return current value
  value
}
```

#### Ref Hook Implementation

The `RefHook` is implemented as a trait with a mutable `current` property and a type parameter:

```scala
trait RefHook extends Hook {
  type RefType
  var current: RefType
}
```

When `useRef` is called:
1. It first gets the current component instance
2. It checks if a ref hook already exists at the current hook index
3. If it exists, it returns the existing ref
4. If not, it creates a new ref hook with the provided initial value
5. The hook index is incremented

The ref attaching mechanism works through the virtual DOM:
1. When an element with a `ref` attribute is created
2. During DOM creation, the element node sets the `current` property of the ref
3. This happens during the commit phase when actual DOM nodes are created

#### Memo Hook Implementation

The `MemoHook` is implemented as a case class:

```scala
case class MemoHook[T](
  var value: T,
  var deps: Seq[Any]
) extends Hook
```

When `useMemo` is called:
1. It first gets the current component instance
2. It checks if a memo hook already exists at the current hook index
3. If it exists:
    - It compares the dependencies with the stored dependencies
    - If they're the same, it returns the stored value
    - If they're different, it recomputes the value and updates the hook
4. If it doesn't exist, it creates a new memo hook with the computed value
5. The hook index is incremented

#### Callback Hook Implementation

`useCallback` is implemented as a thin wrapper around `useMemo`:

```scala
def useCallback[T](callback: T, deps: Seq[Any]): T = useMemo(() => callback, deps)
```

It leverages the same memoization logic but specifically for function references, making the intent clearer in the code.

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

### Using Refs to Integrate with Third-party Libraries

```scala
def ThirdPartyIntegration = () => {
  val chartRef = useRef[dom.html.Div]()
  val chartInstanceRef = useRef[Any](null) // Store the library instance
  
  val (data, setData, _) = useState(List[DataPoint]())
  
  // Set up chart on mount
  useEffect(
    () => {
      if (chartRef.current != null) {
        // Initialize third-party chart
        val chartOptions = js.Dynamic.literal(/* options */)
        val chartInstance = js.Dynamic.global.ThirdPartyChart.init(chartRef.current, data.toJSArray, chartOptions)
        
        // Store instance for cleanup and updates
        chartInstanceRef.current = chartInstance
        
        // Cleanup function to destroy chart
        () => {
          val instance = chartInstanceRef.current.asInstanceOf[js.Dynamic]
          if (instance != null) {
            instance.destroy()
            chartInstanceRef.current = null
          }
        }
      }
    },
    Seq() // Empty deps - run once on mount
  )
  
  // Update chart when data changes
  useEffect(
    () => {
      val instance = chartInstanceRef.current.asInstanceOf[js.Dynamic]
      if (instance != null) {
        instance.updateData(data.toJSArray)
      }
    },
    Seq(data) // Run when data changes
  )
  
  div(
    div(cls := "chart-container", ref := chartRef),
    button(onClick := (() => setData(generateNewData())), "Update Data")
  )
}
```

### Optimizing Child Renders with useCallback and useMemo

```scala
def ParentComponent = () => {
  val (items, setItems, _) = useState(List[Item]())
  val (query, setQuery, _) = useState("")
  
  // Memoize filtered items - only recompute when items or query changes
  val filteredItems = useMemo(
    () => items.filter(_.name.contains(query)),
    Seq(items, query)
  )
  
  // Stable callback for item selection - only recreate when setItems changes
  val handleSelectItem = useCallback(
    (id: String) => {
      setItems(items.map(item => 
        if (item.id == id) item.copy(selected = !item.selected)
        else item
      ))
    },
    Seq(setItems)
  )
  
  div(
    input(
      cls := "search",
      placeholder := "Search items...",
      value := query,
      onInput := ((e: dom.Event) => setQuery(e.target.asInstanceOf[dom.html.Input].value))
    ),
    
    // Pass stable props to child component to prevent unnecessary rerenders
    ItemList <> ItemListProps(
      items = filteredItems,
      onSelectItem = handleSelectItem
    )
  )
}

// Child component that receives stable props
def ItemList = (props: ItemListProps) => {
  // This component will only re-render when its props change
  ul(
    cls := "item-list",
    props.items.map(item => 
      li(
        key := item.id,
        onClick := (() => props.onSelectItem(item.id)),
        cls := s"item ${if (item.selected) "selected" else ""}",
        item.name
      )
    )
  )
}
```

### Measuring DOM Elements with useRef and useEffect

```scala
def MeasurementComponent = () => {
  val elementRef = useRef[dom.html.Div]()
  val (dimensions, setDimensions, _) = useState(Map[String, Double]())
  
  // Measure the element when it mounts
  useEffect(
    () => {
      if (elementRef.current != null) {
        val rect = elementRef.current.getBoundingClientRect()
        setDimensions(Map(
          "width" -> rect.width,
          "height" -> rect.height,
          "top" -> rect.top,
          "left" -> rect.left
        ))
      }
      ()
    },
    Seq() // Empty deps - run once on mount
  )
  
  div(
    div(
      ref := elementRef,
      cls := "measured-element",
      "This element is being measured"
    ),
    p(s"Dimensions: ${dimensions.map { case (k, v) => s"$k: ${v.round}" }.mkString(", ")}")
  )
}
```

### Signal-based State Management Example

```scala
// Create shared signals at module level
object AppState {
  // Authentication state
  val isLoggedIn = Var[Boolean](false)
  val currentUser = Var[Option[User]](None)
  
  // Application data
  val items = Var[List[Item]](List())
  val selectedItemId = Var[Option[String]](None)
  
  // Derived signals
  val selectedItem = selectedItemId.signal.combineWith(items.signal) { (id, itemsList) =>
    id.flatMap(idVal => itemsList.find(_.id == idVal))
  }
  
  // Actions
  def login(username: String, password: String): Future[Boolean] = {
    // Authentication logic
    authService.login(username, password).map { user =>
      // Update multiple signals in a single transaction
      Transaction { _ =>
        isLoggedIn.set(true)
        currentUser.set(Some(user))
      }
      true
    }
  }
  
  def logout(): Unit = {
    Transaction { _ =>
      isLoggedIn.set(false)
      currentUser.set(None)
      selectedItemId.set(None)
    }
  }
  
  def fetchItems(): Unit = {
    itemsService.getItems().foreach { newItems =>
      Transaction { _ => 
        items.set(newItems)
      }
    }
  }
}

// Using the signals in components
def Header = () => {
  val isLoggedIn = useSignal(AppState.isLoggedIn)
  val user = useSignal(AppState.currentUser)
  
  div(
    cls := "header",
    if (isLoggedIn) {
      div(
        span(s"Welcome, ${user.map(_.name).getOrElse("User")}"),
        button(
          onClick := (() => AppState.logout()),
          "Logout"
        )
      )
    } else {
      button(
        onClick := (() => navigateToLogin()),
        "Login"
      )
    }
  )
}

def ItemList = () => {
  val items = useSignal(AppState.items)
  val selectedId = useSignal(AppState.selectedItemId)
  
  // Load items on mount
  useEffect(
    () => {
      AppState.fetchItems()
      ()
    },
    Seq()
  )
  
  ul(
    items.map(item =>
      li(
        key := item.id,
        cls := s"item ${if (selectedId.contains(item.id)) "selected" else ""}",
        onClick := (() => Transaction(_ => AppState.selectedItemId.set(Some(item.id)))),
        item.name
      )
    )
  )
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

8. **Using Refs Properly**:
    - Don't overuse refs - prefer declarative approaches when possible
    - Clean up refs in useEffect's cleanup function
    - Be careful with ref.current in render functions
    - Ensure proper type safety when using refs

9. **Optimization with useMemo and useCallback**:
    - Only memoize values that are expensive to compute
    - Ensure dependency arrays are complete and accurate
    - Consider the cost of memoization itself
    - Use callback memoization primarily for child component optimization

10. **Dependency Array Management**:
    - Include all dependencies that change over time
    - Avoid putting objects or functions directly in dependency arrays
    - Use primitive values when possible
    - Consider extracting values from objects for more precise dependency tracking

11. **Signal Management**:
    - Always use Transactions when updating signals
    - Initialize signals outside components for sharing
    - Keep signal updates logic together
    - Organize related signals in logical groupings or stores
    - Remember that signals maintain their own subscription lifecycle

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
