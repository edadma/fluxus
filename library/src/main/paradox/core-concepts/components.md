# Components

Components are the building blocks of Fluxus applications. They encapsulate rendering logic, state, and behavior into reusable pieces.

## Component Types

In Fluxus, there is a single component type - function components. These are simple functions that take props and return a `FluxusNode`.

### Function Components

Function components are defined as functions that accept a `Props` type parameter and return a `FluxusNode`:

```scala
case class UserProps(name: String, age: Int)

val UserProfile = (props: UserProps) => {
  div(
    cls := "user-profile",
    h2(s"${props.name}"),
    p(s"Age: ${props.age}")
  )
}
```

## Using Components

To use a component, you call it with the `<>` operator and its props:

```scala
UserProfile <> UserProps("John Doe", 30)
```

For components that don't need props, you can use the `NoProps` type:

```scala
val Header = (_: NoProps) => {
  header(
    cls := "app-header",
    h1("My Application")
  )
}

// Usage
Header <> noProps
```

Alternatively, you can define a component that takes `Unit`:

```scala
val Footer = () => {
  footer(
    p("© 2025 My Company")
  )
}

// Usage
Footer <> ()
```

## Composing Components

Components can be composed to build more complex UIs:

```scala
case class AppProps(user: User)

val App = (props: AppProps) => {
  div(
    cls := "app",
    Header <> noProps,
    UserProfile <> UserProps(props.user.name, props.user.age),
    Footer <> ()
  )
}
```

## Component Props

Props (short for "properties") are the inputs to a component. In Fluxus, props are defined as case classes:

```scala
case class ButtonProps(
  label: String,
  onClick: () => Unit,
  disabled: Boolean = false,
  variant: String = "primary"
)
```

This provides type safety and makes it clear what props a component expects.

## Keys

When rendering lists of components, it's important to provide a `key` property to help Fluxus identify which items have changed, been added, or been removed:

```scala
case class TodoItemProps(
  key: String,  // Including key in the props is recommended
  text: String,
  completed: Boolean,
  onToggle: () => Unit
)

val TodoList = (props: TodoListProps) => {
  ul(
    cls := "todo-list",
    props.items.map(item => 
      TodoItem <> TodoItemProps(
        key = item.id,
        text = item.text,
        completed = item.completed,
        onToggle = () => props.onToggle(item.id)
      )
    )
  )
}
```

## Refs and Component References

Fluxus supports forwarding refs to components using the `forwardRef` function:

```scala
case class CustomInputProps(
  label: String,
  placeholder: String = ""
)

val CustomInput = forwardRef[CustomInputProps] { (props, ref) =>
  div(
    cls := "form-control",
    label(props.label),
    input(
      typ := "text",
      placeholder := props.placeholder,
      ref := ref
    )
  )
}

// Usage
val inputRef = useRef[dom.html.Input]()
CustomInput <> (CustomInputProps("Username", "Enter username"), inputRef)

// Later you can access the DOM element
inputRef.current.focus()
```

## Component Best Practices

1. **Keep components focused**: Each component should ideally do one thing well.

2. **Props should be immutable**: Treat props as immutable values.

3. **Use hooks for state**: For components that need state, use hooks like `useState` rather than storing state outside the component.

4. **Extract complex logic**: If a component gets too complex, extract parts into separate components or custom hooks.

5. **Naming conventions**: Use PascalCase for component names and camelCase for props and local variables.

6. **Don't modify props**: Treat props as read-only. If you need to modify a value, create local state based on the prop.

7. **Pure rendering**: Component rendering should be a pure function of props and state, without side effects.