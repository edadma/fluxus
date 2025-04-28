# Hooks

Hooks are functions that let you "hook into" component state and lifecycle features from function components. They provide a way to use state and other Fluxus features without writing classes.

## Rules of Hooks

Hooks in Fluxus follow specific rules:

1. **Only call hooks at the top level**: Don't call hooks inside loops, conditions, or nested functions.

2. **Only call hooks from function components**: Don't call hooks from regular functions.

3. **Hooks must be called in the same order on every render**: The order of hook calls allows Fluxus to maintain the correct state for each hook.

## Basic Hooks

### useState

`useState` is used to add state to a function component:

```scala
val (count, setCount, updateCount) = useState(0)

// Direct update
setCount(count + 1)

// Functional update (safer for async updates)
updateCount(prevCount => prevCount + 1)
```

The `useState` hook returns a triple:
- The current state value
- A function to set the state directly
- A function to update the state based on the previous value

### useEffect

`useEffect` lets you perform side effects in function components:

```scala
// Effect runs after every render
useEffect(() => {
  document.title = s"Count: $count"
})

// Effect runs only when count changes
useEffect(() => {
  document.title = s"Count: $count"
}, Seq(count))

// Effect runs only once (on mount)
useEffect(() => {
  val subscription = subscribeToData()
  
  // Cleanup function (runs before next effect or on unmount)
  () => subscription.unsubscribe()
}, Seq())
```

The `useEffect` hook takes a function to run after the component renders, and an optional dependency array. If any value in the dependency array changes, the effect will run again.

### useRef

`useRef` creates a mutable reference that persists for the lifetime of the component:

```scala
val inputRef = useRef[dom.html.Input]()

input(
  ref := inputRef,
  typ := "text" 
)

// Later, you can access the DOM element
button(
  onClick := (() => inputRef.current.focus()),
  "Focus input"
)
```

The `useRef` hook is useful for:
- Accessing DOM elements directly
- Keeping a mutable value that doesn't trigger re-renders when changed
- Storing values that need to persist between renders

### useMemo

`useMemo` memoizes expensive calculations so they only recompute when dependencies change:

```scala
val memoizedValue = useMemo(
  () => {
    computeExpensiveValue(a, b)
  },
  Seq(a, b)
)
```

### useCallback

`useCallback` memoizes callback functions to prevent unnecessary re-renders of child components:

```scala
val memoizedCallback = useCallback(
  () => {
    doSomething(a, b)
  },
  Seq(a, b)
)
```

## Additional Hooks

### useFetch

`useFetch` is a custom hook in Fluxus for fetching data from APIs:

```scala
case class User(id: Int, name: String) derives JsonDecoder

val (state, retry) = useFetch[List[User]](
  url = "https://api.example.com/users",
  options = FetchOptions(
    method = "GET",
    headers = Map("Content-Type" -> "application/json")
  )
)

div(
  state match {
    case FetchState.Loading() =>
      p("Loading...")
    case FetchState.Success(users) =>
      ul(users.map(user => li(user.name)))
    case FetchState.Error(error) =>
      div(
        p(s"Error: ${error.getMessage}"),
        button(onClick := (() => retry()), "Retry")
      )
    case FetchState.Idle() =>
      p("No request sent yet")
  }
)
```

### useSignal

`useSignal` integrates with Airstream for reactive programming:

```scala
val signal = Var(0) // Airstream signal

val App = () => {
  val count = useSignal(signal)
  
  div(
    p(s"Count: $count"),
    button(
      onClick := (() => signal.update(_ + 1)),
      "Increment"
    )
  )
}
```

## Creating Custom Hooks

You can create your own hooks to reuse stateful logic between components:

```scala
def useWindowSize(): (Int, Int) = {
  val (width, setWidth, _) = useState(dom.window.innerWidth)
  val (height, setHeight, _) = useState(dom.window.innerHeight)
  
  useEffect(() => {
    val handleResize = () => {
      setWidth(dom.window.innerWidth)
      setHeight(dom.window.innerHeight)
    }
    
    dom.window.addEventListener("resize", handleResize)
    
    () => dom.window.removeEventListener("resize", handleResize)
  }, Seq())
  
  (width, height)
}

// Usage
val (width, height) = useWindowSize()
```

Custom hooks should:
- Start with "use" to follow the naming convention
- Call other hooks according to the rules of hooks
- Return values that the component will use

By extracting common logic into custom hooks, you can keep your components focused on rendering and improve code reuse across your application.