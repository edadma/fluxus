# Custom Hooks

Custom hooks are a powerful pattern for reusing stateful logic between components. They allow you to extract component logic into reusable functions.

## Basic Pattern

A custom hook is simply a function that:

1. Starts with "use" (by convention)
2. Calls other hooks
3. Returns values that components will use

```scala
def useCounter(initialValue: Int = 0): (Int, () => Unit, () => Unit) = {
  val (count, setCount, _) = useState(initialValue)
  
  val increment = () => setCount(count + 1)
  val decrement = () => setCount(count - 1)
  
  (count, increment, decrement)
}

// Usage in a component
val CounterComponent = () => {
  val (count, increment, decrement) = useCounter(0)
  
  div(
    p(s"Count: $count"),
    button(onClick := decrement, "−"),
    button(onClick := increment, "+")
  )
}
```

## Why Create Custom Hooks?

Custom hooks provide several benefits:

1. **Code Reuse**: Extract common logic into reusable functions
2. **Separation of Concerns**: Split complex components into smaller, focused pieces
3. **Cleaner Components**: Move implementation details out of component bodies
4. **Testing**: Test complex logic independently from UI components
5. **Composition**: Compose multiple hooks together for more complex behavior

## Examples

### Form Field Hook

```scala
def useFormField(initialValue: String = ""): (String, dom.Event => Unit, Boolean) = {
  val (value, setValue, _) = useState(initialValue)
  val (touched, setTouched, _) = useState(false)
  
  val handleChange = (e: dom.Event) => {
    setValue(e.target.asInstanceOf[dom.html.Input].value)
    setTouched(true)
  }
  
  (value, handleChange, touched)
}

// Usage
val RegisterForm = () => {
  val (username, handleUsernameChange, usernameTouched) = useFormField()
  val (email, handleEmailChange, emailTouched) = useFormField()
  
  div(
    div(
      label("Username"),
      input(
        typ := "text",
        value := username,
        onInput := handleUsernameChange
      ),
      if (usernameTouched && username.isEmpty) {
        p(cls := "error", "Username is required")
      }
    ),
    div(
      label("Email"),
      input(
        typ := "email",
        value := email,
        onInput := handleEmailChange
      ),
      if (emailTouched && email.isEmpty) {
        p(cls := "error", "Email is required")
      }
    )
  )
}
```

### Local Storage Hook

```scala
def useLocalStorage[T: JsonEncoder: JsonDecoder](key: String, initialValue: T): (T, T => Unit) = {
  // Function to get stored value
  def getStoredValue(): T = {
    try {
      Option(dom.window.localStorage.getItem(key))
        .flatMap(item => item.fromJson[T].toOption)
        .getOrElse(initialValue)
    } catch {
      case _: Exception => initialValue
    }
  }
  
  // Initialize state with stored value
  val (storedValue, setStoredValue, _) = useState(getStoredValue())
  
  // Return a wrapped version of useState's setter function
  val setValue = (value: T) => {
    try {
      // Allow value to be a function
      setStoredValue(value)
      // Save to localStorage
      dom.window.localStorage.setItem(key, value.toJson)
    } catch {
      case e: Exception =>
        console.error(s"Error saving to localStorage: ${e.getMessage}")
    }
  }
  
  (storedValue, setValue)
}

// Usage
val ThemeSelector = () => {
  val (theme, setTheme) = useLocalStorage("theme", "light")
  
  div(
    p(s"Current theme: $theme"),
    button(
      onClick := (() => setTheme(if (theme == "light") "dark" else "light")),
      "Toggle Theme"
    )
  )
}
```

### Window Size Hook

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
    
    // Cleanup function
    () => dom.window.removeEventListener("resize", handleResize)
  }, Seq())
  
  (width, height)
}

// Usage
val ResponsiveComponent = () => {
  val (width, height) = useWindowSize()
  
  div(
    p(s"Window dimensions: $width × $height"),
    if (width < 768) {
      div(cls := "mobile-layout", "Mobile Layout")
    } else {
      div(cls := "desktop-layout", "Desktop Layout")
    }
  )
}
```

### Debounce Hook

```scala
def useDebounce[T](value: T, delay: Int = 500): T = {
  val (debouncedValue, setDebouncedValue, _) = useState(value)
  
  useEffect(() => {
    val handler = dom.window.setTimeout(() => {
      setDebouncedValue(value)
    }, delay)
    
    () => dom.window.clearTimeout(handler)
  }, Seq(value, delay))
  
  debouncedValue
}

// Usage
val SearchComponent = () => {
  val (searchTerm, setSearchTerm, _) = useState("")
  val debouncedSearchTerm = useDebounce(searchTerm, 300)
  
  // Effect runs when debouncedSearchTerm changes
  useEffect(() => {
    if (debouncedSearchTerm.nonEmpty) {
      performSearch(debouncedSearchTerm)
    }
  }, Seq(debouncedSearchTerm))
  
  div(
    input(
      typ := "text",
      value := searchTerm,
      onInput := ((e: dom.Event) => 
        setSearchTerm(e.target.asInstanceOf[dom.html.Input].value)
      ),
      placeholder := "Search..."
    ),
    p(s"Searching for: $debouncedSearchTerm")
  )
}
```

### Network Status Hook

```scala
def useOnlineStatus(): Boolean = {
  val (isOnline, setIsOnline, _) = useState(dom.window.navigator.onLine)
  
  useEffect(() => {
    val handleOnline = () => setIsOnline(true)
    val handleOffline = () => setIsOnline(false)
    
    dom.window.addEventListener("online", handleOnline)
    dom.window.addEventListener("offline", handleOffline)
    
    () => {
      dom.window.removeEventListener("online", handleOnline)
      dom.window.removeEventListener("offline", handleOffline)
    }
  }, Seq())
  
  isOnline
}

// Usage
val NetworkIndicator = () => {
  val isOnline = useOnlineStatus()
  
  div(
    cls := s"network-status ${if (isOnline) "online" else "offline"}",
    if (isOnline) {
      span("Connected")
    } else {
      span("Disconnected")
    }
  )
}
```

## Composing Hooks

Custom hooks can call other custom hooks, allowing for powerful composition:

```scala
def useUserProfile(userId: String): (Option[User], Boolean, Option[String]) = {
  val isOnline = useOnlineStatus()
  val (userState, retry) = useFetch[User](
    url = if (isOnline) s"/api/users/$userId" else "",
    dependencies = Seq(userId, isOnline)
  )
  
  userState match {
    case FetchState.Success(user) => (Some(user), false, None)
    case FetchState.Loading() => (None, true, None)
    case FetchState.Error(error) => (None, false, Some(error.getMessage))
    case FetchState.Idle() => (None, false, None)
  }
}

// Usage
val ProfilePage = (props: ProfilePageProps) => {
  val (user, loading, error) = useUserProfile(props.userId)
  
  div(
    cls := "profile-page",
    if (loading) {
      div("Loading profile...")
    } else if (error.isDefined) {
      div(cls := "error", s"Error: ${error.get}")
    } else if (user.isDefined) {
      div(
        h1(user.get.name),
        p(user.get.email)
      )
    } else {
      div("No user data")
    }
  )
}
```

## Rules for Custom Hooks

1. **Follow Hook Rules**: Custom hooks must follow the same rules as built-in hooks
2. **Naming Convention**: Always start custom hook names with "use"
3. **Return Immutable Values**: Return values that components can use directly
4. **Handle Cleanup**: If your hook sets up resources, make sure they're cleaned up
5. **Keep it Focused**: Each hook should have a single responsibility
6. **Document Types**: Be explicit about the types your hook returns

## Testing Custom Hooks

Custom hooks can be tested independently from components:

```scala
// TestUtils.scala
def testHook[T](hook: () => T): T = {
  var result: T = null.asInstanceOf[T]
  
  val TestComponent = () => {
    result = hook()
    null
  }
  
  createDOM(TestComponent <> (), document.createElement("div"))
  
  result
}

// CounterHookTest.scala
test("useCounter should initialize with the provided value") {
  val (count, _, _) = testHook(() => useCounter(5))
  count shouldBe 5
}

test("useCounter increment should increase the count") {
  var count: Int = 0
  var increment: () => Unit = () => {}
  
  testHook(() => {
    val result = useCounter(0)
    count = result._1
    increment = result._2
    result
  })
  
  increment()
  count shouldBe 1
}
```

## Best Practices

1. **Extract Reusable Logic**: If you find yourself copying and pasting logic between components, consider creating a custom hook.

2. **Focus on State and Side Effects**: Custom hooks should primarily manage state and side effects. Avoid putting rendering logic in hooks.

3. **Keep Components Pure**: Components should focus on rendering UI based on props and hook results, while hooks handle the complex state logic.

4. **Avoid Premature Abstraction**: Don't create hooks for everything. Only extract logic when it's clearly reusable.

5. **Expose Minimal Interface**: Only return what components need, hiding implementation details.

6. **Name Hooks Based on Purpose**: Use descriptive names for your hooks (e.g., `useUserStatus` instead of `useStatus`).

7. **Library Organization**: Group related hooks in modules for better organization.

## Common Hook Patterns

### Resource Hook Pattern

For managing external resources:

```scala
def useResource[T](
  acquire: () => T,
  release: T => Unit,
  deps: Seq[Any] = Seq()
): T = {
  val (resource, setResource, _) = useState[T](null.asInstanceOf[T])
  
  useEffect(() => {
    val newResource = acquire()
    setResource(newResource)
    
    () => {
      if (resource != null) {
        release(resource)
      }
    }
  }, deps)
  
  resource
}
```

### Async Hook Pattern

For handling asynchronous operations:

```scala
def useAsync[T](
  asyncFn: () => Future[T],
  immediate: Boolean = true,
  deps: Seq[Any] = Seq()
): (Option[T], Boolean, Option[Throwable], () => Unit) = {
  val (value, setValue, _) = useState[Option[T]](None)
  val (loading, setLoading, _) = useState(false)
  val (error, setError, _) = useState[Option[Throwable]](None)
  
  val execute = () => {
    setLoading(true)
    setError(None)
    
    asyncFn()
      .map { result =>
        setValue(Some(result))
        setLoading(false)
      }
      .recover { case e: Throwable =>
        setError(Some(e))
        setLoading(false)
      }
  }
  
  useEffect(() => {
    if (immediate) {
      execute()
    }
  }, deps)
  
  (value, loading, error, execute)
}
```

### Reducer Hook Pattern

For complex state logic:

```scala
def useReducer[S, A](
  reducer: (S, A) => S,
  initialState: S
): (S, A => Unit) = {
  val (state, setState, _) = useState(initialState)
  
  val dispatch = (action: A) => {
    setState(prevState => reducer(prevState, action))
  }
  
  (state, dispatch)
}
```