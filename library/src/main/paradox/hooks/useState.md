# useState

The `useState` hook adds local state to a Fluxus function component. It returns a value and setter functions that you can use to update the state.

## Basic Usage

```scala
val (state, setState, updateState) = useState(initialValue)
```

Where:
- `state` is the current state value
- `setState` is a function to directly set a new state value
- `updateState` is a function that takes a function to update the state based on its previous value

## Examples

### Simple Counter

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

### Using the Update Function

The update function is particularly useful when you need to update state based on the previous value:

```scala
val Counter = () => {
  val (count, _, updateCount) = useState(0)
  
  div(
    p(s"Count: $count"),
    div(
      button(
        onClick := (() => updateCount(_ - 1)),
        "Decrement"
      ),
      button(
        onClick := (() => updateCount(_ + 1)),
        "Increment"
      ),
      button(
        onClick := (() => {
          // Multiple updates in sequence
          updateCount(_ => 0) // Reset to 0
          updateCount(_ + 10) // Then add 10
        }),
        "Reset and Add 10"
      )
    )
  )
}
```

### Form Inputs

```scala
val LoginForm = () => {
  val (username, setUsername, _) = useState("")
  val (password, setPassword, _) = useState("")
  
  def handleSubmit(): Unit = {
    console.log(s"Logging in with: $username / $password")
  }
  
  form(
    div(
      label("Username:"),
      input(
        typ := "text",
        value := username,
        onInput := ((e: dom.Event) => 
          setUsername(e.target.asInstanceOf[dom.html.Input].value)
        )
      )
    ),
    div(
      label("Password:"),
      input(
        typ := "password",
        value := password,
        onInput := ((e: dom.Event) => 
          setPassword(e.target.asInstanceOf[dom.html.Input].value)
        )
      )
    ),
    button(
      typ := "button",
      onClick := (() => handleSubmit()),
      "Log In"
    )
  )
}
```

### Objects and Collections

When using objects or collections as state, always update them immutably:

```scala
case class Todo(id: String, text: String, completed: Boolean)

val TodoApp = () => {
  val (todos, _, updateTodos) = useState(Vector[Todo]())
  val (newTodo, setNewTodo, _) = useState("")
  
  def addTodo(): Unit = {
    if (newTodo.nonEmpty) {
      updateTodos(prevTodos => 
        prevTodos :+ Todo(
          id = java.util.UUID.randomUUID().toString,
          text = newTodo,
          completed = false
        )
      )
      setNewTodo("")
    }
  }
  
  def toggleTodo(id: String): Unit = {
    updateTodos(prevTodos => 
      prevTodos.map(todo => 
        if (todo.id == id) todo.copy(completed = !todo.completed)
        else todo
      )
    )
  }
  
  div(
    div(
      input(
        typ := "text",
        value := newTodo,
        onInput := ((e: dom.Event) => 
          setNewTodo(e.target.asInstanceOf[dom.html.Input].value)
        ),
        placeholder := "Add a todo"
      ),
      button(onClick := (() => addTodo()), "Add")
    ),
    ul(
      todos.map(todo => 
        li(
          key := todo.id,
          input(
            typ := "checkbox",
            checked := todo.completed,
            onChange := (() => toggleTodo(todo.id))
          ),
          span(
            cls := (if (todo.completed) "completed" else ""),
            todo.text
          )
        )
      )
    )
  )
}
```

## Lazy Initialization

If creating the initial state is expensive, you can pass a function to `useState`:

```scala
val (state, setState, _) = useState(() => {
  // Expensive computation
  computeInitialState()
})
```

This function will only be called during the initial render.

## Multiple State Variables

You can call `useState` multiple times in the same component:

```scala
val Form = () => {
  val (name, setName, _) = useState("")
  val (age, setAge, _) = useState(0)
  val (email, setEmail, _) = useState("")
  
  // Component body
}
```

## Batching of Updates

Fluxus automatically batches multiple state updates that occur within the same event handler:

```scala
button(
  onClick := (() => {
    setCount(count + 1) // These will be batched,
    setFlag(true)       // causing only one re-render
  })
)
```

## Functional Updates vs Direct Updates

There's an important difference between the direct update (`setState`) and functional update (`updateState`) functions:

- **Direct update** (`setState`): Sets the state to a new value regardless of the previous value
- **Functional update** (`updateState`): Accepts a function that receives the previous state and returns the new state

When to use each:

1. Use **direct update** when the new state doesn't depend on the previous state:
   ```scala
   setName("John")
   setIsOpen(true)
   ```

2. Use **functional update** when the new state depends on the previous state:
   ```scala
   updateCount(prev => prev + 1)
   updateItems(prev => prev :+ newItem)
   ```

Functional updates are especially important when dealing with asynchronous updates, as they ensure you're always working with the latest state.

## Typed State

Fluxus's `useState` hook is fully typed, allowing the Scala compiler to ensure type safety:

```scala
// The type is inferred from the initial value
val (count, setCount, updateCount) = useState(0)
setCount("not a number") // Compilation error

// You can explicitly specify the type
val (items, setItems, updateItems) = useState[List[String]](List())
```

## Implementation Details

The `useState` hook is implemented using the component instance's hook array. Each component maintains an array of hooks in the order they were called. The `useState` hook adds a `StateHook` to this array.

When a component re-renders, the hooks are matched up by their call order, ensuring that the state is preserved across renders.