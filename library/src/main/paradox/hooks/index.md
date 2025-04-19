# Hooks

Hooks are functions that let you "hook into" Fluxus state and lifecycle features from functional components. They let you use state and other Fluxus features without writing a class.

## Available Hooks

@@@ index

* [useState](useState.md)

@@@

## Rules of Hooks

There are two important rules you need to follow when using hooks:

1. **Only call hooks at the top level of your component**. Don't call hooks inside loops, conditions, or nested functions.

2. **Only call hooks from Fluxus functional components**. Don't call hooks from regular JavaScript functions.

Following these rules ensures that state is preserved correctly between renders.

## Basic Example

Here's a component that uses multiple hooks:

```scala
def ProfileForm = () => {
  // State for form inputs
  val (name, setName, _) = useState("")
  val (bio, setBio, _) = useState("")
  
  // Effect to load data
  useEffect(() => {
    // Load data from an API
    println("Component mounted")
    
    // Return cleanup function
    () => println("Component unmounted")
  }, Seq())
  
  // Computed value
  val isFormValid = useMemo(
    () => name.nonEmpty && bio.length <= 200,
    Seq(name, bio)
  )
  
  div(
    h2("Profile"),
    div(
      label("Name:"),
      input(
        value_ := name,
        onInput := ((e) => setName(e.target.asInstanceOf[org.scalajs.dom.html.Input].value))
      )
    ),
    div(
      label("Bio:"),
      textarea(
        value_ := bio,
        onInput := ((e) => setBio(e.target.asInstanceOf[org.scalajs.dom.html.TextArea].value))
      ),
      div(s"${bio.length}/200 characters")
    ),
    button(
      disabled := !isFormValid,
      "Save Profile"
    )
  )
}
```

## Custom Hooks

You can create your own custom hooks to extract component logic into reusable functions:

```scala
def useFormField[T](initialValue: T): (T, (org.scalajs.dom.Event => Unit), Boolean) = {
  val (value, setValue, _) = useState(initialValue)
  val (touched, setTouched, _) = useState(false)
  
  val handleChange = (e: org.scalajs.dom.Event) => {
    setValue(e.target.asInstanceOf[org.scalajs.dom.html.Input].value.asInstanceOf[T])
    setTouched(true)
  }
  
  (value, handleChange, touched)
}

// Usage in a component
def Form = () => {
  val (username, handleUsernameChange, usernameTouched) = useFormField("")
  val (password, handlePasswordChange, passwordTouched) = useFormField("")
  
  // Rest of the component...
}
```

Custom hooks are a powerful way to share logic between components while keeping your code DRY.