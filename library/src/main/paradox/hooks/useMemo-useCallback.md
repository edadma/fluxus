# useMemo and useCallback Hooks

Both `useMemo` and `useCallback` are optimization hooks that help prevent unnecessary calculations and renders in your Fluxus applications.

## useMemo

`useMemo` lets you memoize expensive calculations so they're only recomputed when dependencies change.

### useMemo Basic Usage

```scala
val memoizedValue = useMemo(
  () => computeExpensiveValue(a, b),
  Seq(a, b)
)
```

Where:
- The first argument is a function that computes a value
- The second argument is an array of dependencies
- The function runs during rendering
- Fluxus will only recompute the memoized value when one of the dependencies has changed

### When to Use useMemo

Use `useMemo` when:

1. You have computationally expensive calculations that don't need to be redone on every render
2. You want to ensure referential equality for objects that would otherwise be recreated on each render
3. You want to avoid unnecessary re-renders of child components that rely on object or array props

### useMemo Examples

#### Expensive Calculation

```scala
val FibonacciCalculator = () => {
  val (number, setNumber, _) = useState(35)
  
  val fibResult = useMemo(
    () => {
      // Recursive Fibonacci - intentionally inefficient to demonstrate memoization
      def fib(n: Int): BigInt = {
        if (n <= 1) n
        else fib(n - 1) + fib(n - 2)
      }
      
      fib(number).toString
    },
    Seq(number) // Only recalculate when number changes
  )
  
  div(
    input(
      typ := "number",
      value := number.toString,
      onInput := ((e: dom.Event) => setNumber(e.target.asInstanceOf[dom.html.Input].value.toInt))
    ),
    p(s"Fibonacci($number) = $fibResult")
  )
}
```

#### Derived Data

```scala
val FilteredList = (props: FilteredListProps) => {
  val (filter, setFilter, _) = useState("")
  
  // Memoize filtered items to avoid recalculating on every render
  val filteredItems = useMemo(
    () => props.items.filter(item => item.name.contains(filter)),
    Seq(props.items, filter) // Recalculate when items or filter changes
  )
  
  div(
    input(
      value := filter,
      onInput := ((e: dom.Event) => setFilter(e.target.asInstanceOf[dom.html.Input].value)),
      placeholder := "Filter items"
    ),
    ul(
      filteredItems.map(item => li(key := item.id, item.name))
    )
  )
}
```

#### Preventing Unnecessary Renders

```scala
case class ItemProps(id: String, name: String, onClick: () => Unit)

val Item = (props: ItemProps) => {
  console.log(s"Rendering Item: ${props.name}")
  
  li(
    onClick := props.onClick,
    props.name
  )
}

val ItemList = () => {
  val (items, _, updateItems) = useState(List("Apple", "Banana", "Cherry"))
  val (selectedIndex, setSelectedIndex, _) = useState(-1)
  
  val itemComponents = useMemo(
    () => {
      items.zipWithIndex.map { case (name, index) =>
        val itemProps = ItemProps(
          id = index.toString,
          name = name,
          onClick = () => setSelectedIndex(index)
        )
        
        Item <> itemProps
      }
    },
    Seq(items) // Only recreate when items change, not when selectedIndex changes
  )
  
  div(
    ul(itemComponents),
    p(
      if (selectedIndex >= 0) s"Selected: ${items(selectedIndex)}"
      else "Nothing selected"
    )
  )
}
```

## useCallback

`useCallback` returns a memoized version of a callback function that only changes if one of the dependencies has changed.

### useCallback Basic Usage

```scala
val memoizedCallback = useCallback(
  () => doSomething(a, b),
  Seq(a, b)
)
```

Where:
- The first argument is a function that you want to memoize
- The second argument is an array of dependencies
- The function is only recreated when one of the dependencies changes

### When to Use useCallback

Use `useCallback` when:

1. You're passing callbacks to optimized child components that rely on reference equality
2. You want to prevent callbacks from being recreated on every render
3. The callback is a dependency for other hooks like `useEffect`

### useCallback Examples

#### Passing Stable Callbacks to Child Components

```scala
// Child component that uses React.memo or similar optimization
case class ButtonProps(onClick: () => Unit, label: String)

val Button = (props: ButtonProps) => {
  console.log(s"Rendering Button: ${props.label}")
  
  button(
    onClick := props.onClick,
    props.label
  )
}

val Parent = () => {
  val (count, setCount, _) = useState(0)
  
  // Without useCallback, this function would be recreated on every render
  val incrementCount = useCallback(
    () => setCount(count + 1),
    Seq(count) // Only recreate when count changes
  )
  
  // This callback doesn't depend on any values from render scope
  val resetCount = useCallback(
    () => setCount(0),
    Seq() // Never recreate this function
  )
  
  div(
    p(s"Count: $count"),
    Button <> ButtonProps(incrementCount, "Increment"),
    Button <> ButtonProps(resetCount, "Reset")
  )
}
```

#### Callbacks as Dependencies of Effects

```scala
val SearchResults = () => {
  val (query, setQuery, _) = useState("")
  val (results, setResults, _) = useState(List.empty[String])
  
  // Memoize the search function
  val searchAPI = useCallback(
    () => {
      if (query.nonEmpty) {
        fetchResults(query).foreach(data => setResults(data))
      }
    },
    Seq(query) // Only recreate when query changes
  )
  
  // Use the memoized function in an effect
  useEffect(() => {
    searchAPI()
  }, Seq(searchAPI)) // Depends on the searchAPI function
  
  div(
    input(
      value := query,
      onInput := ((e: dom.Event) => setQuery(e.target.asInstanceOf[dom.html.Input].value)),
      placeholder := "Search..."
    ),
    button(onClick := (() => searchAPI()), "Search"),
    ul(
      results.map(result => li(result))
    )
  )
}
```

## Differences Between useMemo and useCallback

While similar, these hooks serve different purposes:

- `useMemo` memoizes a **value** resulting from a computation
- `useCallback` memoizes a **function** definition

In fact, `useCallback(fn, deps)` is equivalent to `useMemo(() => fn, deps)`.

## Implementation in Fluxus

In Fluxus, `useCallback` is actually implemented in terms of `useMemo`:

```scala
def useCallback[T](callback: T, deps: Seq[Any]): T = useMemo(() => callback, deps)
```

## Performance Considerations

1. **Don't optimize prematurely**: Only use these hooks when you have identified a performance issue.

2. **Be careful with dependencies**: Missing dependencies can cause bugs, while too many dependencies can negate the benefits of memoization.

3. **Consider the cost**: Memoization itself has a cost. For very simple calculations, the overhead of `useMemo` might be greater than just recalculating the value.

4. **Debugging**: Memoized functions can make debugging more difficult, as the functions you see in the debugger are not the ones you wrote in your code.

## Using with TypeScript/Scala Type System

Both hooks preserve the type of the value or function being memoized:

```scala
// Type is inferred as Int
val memoizedValue = useMemo(() => calculateValue(a, b), Seq(a, b))

// Type is inferred as (Int, Int) => Int
val memoizedFunction = useCallback((a: Int, b: Int) => a + b, Seq())

// Explicit typing
val memoizedList = useMemo[List[String]](() => 
  items.filter(_.contains(query)), 
  Seq(items, query)
)
```

## Edge Cases and Gotchas

1. **Dependencies must be stable**: If you create objects or arrays inside your component, they will be new references on every render. Either move them outside the component or memoize them.

2. **Don't create functions inside useMemo**: This can lead to unexpected behavior.
   ```scala
   // Don't do this
   val memoizedValue = useMemo(() => {
     val helper = () => doSomething()
     helper()
   }, Seq())
   ```

3. **Empty dependencies array vs. null**: With an empty array, the value is computed once. With `null`, it's computed on every render.
   ```scala
   // Computed once
   val value1 = useMemo(() => expensive(), Seq())
   
   // Computed on every render
   val value2 = useMemo(() => expensive(), null)
   ```

4. **Infinite loops**: Be careful not to create dependencies that change on every render.

5. **Non-serializable values**: The dependency array should contain values that can be compared with `==`.