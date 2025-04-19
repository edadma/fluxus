# Event Handling

Fluxus provides an ergonomic and type-safe way to handle DOM events in your components.

## Basic Event Handling

Event handlers are attached to elements using attributes with the `on` prefix:

```scala
button(
  onClick := (() => console.log("Button clicked!")),
  "Click me"
)
```

## Event Types

Fluxus supports all standard DOM events, including:

- **Mouse events**: onClick, onMouseDown, onMouseUp, onMouseEnter, onMouseLeave, etc.
- **Keyboard events**: onKeyDown, onKeyUp, onKeyPress
- **Form events**: onChange, onSubmit, onInput, onFocus, onBlur
- **Drag events**: onDrag, onDragStart, onDragEnd, onDrop, etc.
- **Touch events**: onTouchStart, onTouchMove, onTouchEnd
- **And more**: onScroll, onWheel, onCopy, onPaste, etc.

## Accessing Event Information

Event handlers can access the event object to get more information:

```scala
input(
  onInput := ((e: dom.Event) => {
    val value = e.target.asInstanceOf[dom.html.Input].value
    setInputValue(value)
  }),
  typ := "text"
)
```

For keyboard events, you can check specific keys:

```scala
input(
  onKeyDown := ((e: dom.KeyboardEvent) => {
    if (e.key == "Enter") {
      submitForm()
    }
  }),
  typ := "text"
)
```

## Preventing Default Behavior

To prevent the default browser behavior for an event, use `preventDefault()`:

```scala
form(
  onSubmit := ((e: dom.Event) => {
    e.preventDefault()
    // Handle form submission manually
    submitForm()
  }),
  // Form elements
)
```

## Event Propagation

Events in the DOM bubble up from the target element to its ancestors. You can stop this propagation using `stopPropagation()`:

```scala
div(
  onClick := (() => console.log("Outer div clicked")),
  div(
    onClick := ((e: dom.Event) => {
      e.stopPropagation()
      console.log("Inner div clicked")
    }),
    "Click me"
  )
)
```

## Event Delegation

For large lists, you can use event delegation by attaching a single event handler to a parent element:

```scala
ul(
  onClick := ((e: dom.Event) => {
    val target = e.target.asInstanceOf[dom.Element]
    if (target.nodeName.toLowerCase == "li") {
      val id = target.getAttribute("data-id")
      handleItemClick(id)
    }
  }),
  items.map(item => 
    li("data-id" := item.id, item.name)
  )
)
```

## Custom Event Types

For more type safety, you can define custom event handler types:

```scala
type InputChangeHandler = dom.Event => Unit
type SubmitHandler = dom.Event => Unit

// Usage
def FormComponent() = {
  val handleInputChange: InputChangeHandler = e => {
    val value = e.target.asInstanceOf[dom.html.Input].value
    setInputValue(value)
  }
  
  val handleSubmit: SubmitHandler = e => {
    e.preventDefault()
    submitForm()
  }
  
  form(
    onSubmit := handleSubmit,
    input(onInput := handleInputChange)
  )
}
```

## Memory Management

Fluxus automatically cleans up event listeners when components are unmounted, so you don't need to worry about memory leaks from event handlers.

## Events in Custom Components

When creating custom components, you can pass event handlers as props:

```scala
case class ButtonProps(
  onClick: () => Unit,
  label: String
)

val Button = (props: ButtonProps) => {
  button(
    cls := "custom-button",
    onClick := props.onClick,
    props.label
  )
}

// Usage
Button <> ButtonProps(() => console.log("Custom button clicked"), "Click me")
```

## Event Object Properties

Common properties of DOM event objects include:

- `target`: The element that triggered the event
- `currentTarget`: The element that the event handler is attached to
- `preventDefault()`: Prevents the default action
- `stopPropagation()`: Stops the event from bubbling up
- `timeStamp`: The time when the event was created
- `type`: The type of event (e.g., "click", "keydown")

Additional properties are available for specific event types:

- `MouseEvent`: clientX, clientY, button
- `KeyboardEvent`: key, keyCode, ctrlKey, altKey, shiftKey
- `InputEvent`: data, inputType
- `TouchEvent`: touches, changedTouches

## Example: Form Handling

Here's a complete example of form handling with events:

```scala
def LoginForm() = {
  val (username, setUsername, _) = useState("")
  val (password, setPassword, _) = useState("")
  val (error, setError, _) = useState("")
  
  def handleSubmit(e: dom.Event) = {
    e.preventDefault()
    
    if (username.isEmpty || password.isEmpty) {
      setError("Please fill in all fields")
    } else {
      setError("")
      // Submit the form
      loginUser(username, password)
    }
  }
  
  form(
    onSubmit := handleSubmit,
    
    if (error.nonEmpty) {
      div(cls := "error", error)
    },
    
    div(
      label("Username"),
      input(
        typ := "text",
        value := username,
        onInput := ((e: dom.Event) => 
          setUsername(e.target.asInstanceOf[dom.html.Input].value)
        )
      )
    ),
    
    div(
      label("Password"),
      input(
        typ := "password",
        value := password,
        onInput := ((e: dom.Event) => 
          setPassword(e.target.asInstanceOf[dom.html.Input].value)
        )
      )
    ),
    
    button(typ := "submit", "Log in")
  )
}
```