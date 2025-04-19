# useState

The `useState` hook lets you add state to your functional components. It's the primary way to introduce reactivity into your UI.

## Signature

```scala
def useState[T](initial: T): (T, T => Unit, (T => T) => Unit)
```

## Parameters

- `initial`: The initial state value

## Return Value

A tuple containing:
1. The current state value
2. A function to directly set the state to a new value
3. A function to update the state based on the previous value

## Basic Usage

```scala
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
```

## Direct Updates vs. Functional Updates

You can update state in two ways:

### Direct Updates

Use the second return value to set a new state value directly:

```scala
val (count, setCount, _) = useState(0)

// Direct update
setCount(5) // Sets count to 5 regardless of current value
```

### Functional Updates

Use the third return value to update state based on the previous value:

```scala
val (count, _, updateCount) = useState(0)

// Functional update
updateCount(prevCount => prevCount + 1)
```

Functional updates are particularly useful when the new state depends on the previous state, especially in cases where multiple updates might be batched together.

## Examples

### Simple Counter

```scala
def Counter = () => {
  val (count, setCount, _) = useState(0)
  
  div(
    div(s"Count: $count"),
    button(onClick := (() => setCount(count + 1)), "Increment"),
    button(onClick := (() => setCount(count - 1)), "Decrement"),
    button(onClick := (() => setCount(0)), "Reset")
  )
}
```

### Form Input

```scala
def FormExample = () => {
  val (name, setName, _) = useState("")
  
  div(
    div("Enter your name:"),
    input(
      value_ := name,
      onInput := ((e: org.scalajs.dom.Event) => 
        setName(e.target.asInstanceOf[org.scalajs.dom.html.Input].value)
      )
    ),
    if (name.nonEmpty) 
      p(s"Hello, $name!")
    else
      null
  )
}
```

### Complex State with Case Classes

```scala
case class User(name: String, email: String)

def UserForm = () => {
  val (user, setUser, updateUser) = useState(User("", ""))
  
  div(
    div("Name:"),
    input(
      value_ := user.name,
      onInput := ((e: org.scalajs.dom.Event) => 
        updateUser(prev => prev.copy(name = e.target.asInstanceOf[org.scalajs.dom.html.Input].value))
      )
    ),
    div("Email:"),
    input(
      value_ := user.email,
      onInput := ((e: org.scalajs.dom.Event) => 
        updateUser(prev => prev.copy(email = e.target.asInstanceOf[org.scalajs.dom.html.Input].value))
      )
    ),
    div(s"Current user: ${user.name} (${user.email})")
  )
}
```

## Best Practices

1. **Use multiple state hooks for unrelated state variables** rather than combining everything into one complex state object.

2. **Use functional updates when the new state depends on the previous state** to avoid issues with stale closures.

3. **Keep state minimal and derived values computed** - don't store values that can be computed from other state.

4. **Use the updater function** (`updateCount` in our examples) for state that depends on previous state, especially inside event handlers or effects.

## Limitations

- Hooks, including `useState`, can only be called at the top level of your component function, not inside loops, conditions, or nested functions.

- The state is maintained as long as the component is mounted. When a component is unmounted, its state is destroyed.

## See Also

