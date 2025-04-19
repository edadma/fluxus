# useEffect

The `useEffect` hook lets you perform side effects in function components. It serves a similar purpose to lifecycle methods in class-based component systems, but unified into a single API.

## Basic Usage

```scala
useEffect(() => {
  // Side effect code
  
  // Optional cleanup function
  () => {
    // Cleanup code
  }
}, dependencies)
```

Where:
- The first argument is a function that contains the side effect code
- The function can optionally return a cleanup function
- The second argument is an optional array of dependencies

## Timing of Effects

Effects run after the browser has painted, so they don't block the browser from updating the screen. This makes your app feel more responsive.

## Types of Effects

### Effects Without Cleanup

Some effects don't require any cleanup:

```scala
val DocumentTitleComponent = (props: TitleProps) => {
  useEffect(() => {
    // Update the document title using the browser API
    document.title = props.title
  }, Seq(props.title))
  
  // ...
}
```

In this example, the effect runs when the component mounts and whenever `props.title` changes. There's no need for cleanup because updating the document title doesn't create any persistent resources.

### Effects with Cleanup

Some effects create resources that need to be cleaned up:

```scala
val MouseTracker = () => {
  val (position, setPosition, _) = useState((0, 0))
  
  useEffect(() => {
    // Set up event listener
    val handleMouseMove = (e: dom.MouseEvent) => {
      setPosition((e.clientX, e.clientY))
    }
    
    document.addEventListener("mousemove", handleMouseMove)
    
    // Return a cleanup function
    () => {
      document.removeEventListener("mousemove", handleMouseMove)
    }
  }, Seq())
  
  div(s"Mouse position: ${position._1}, ${position._2}")
}
```

The cleanup function will run:
- Before the component unmounts
- Before the effect runs again (if the dependencies change)

## Controlling When Effects Run

The dependencies array controls when the effect runs:

1. **No dependencies array**: The effect runs after every render
   ```scala
   useEffect(() => {
     console.log("This runs after every render")
   })
   ```

2. **Empty dependencies array**: The effect runs only once after the initial render
   ```scala
   useEffect(() => {
     console.log("This runs only on mount")
   }, Seq())
   ```

3. **Dependencies specified**: The effect runs after the initial render and when any dependency changes
   ```scala
   useEffect(() => {
     console.log(s"The count is now: $count")
   }, Seq(count))
   ```

## Examples

### Data Fetching

```scala
val UserProfile = (props: UserProfileProps) => {
  val (user, setUser, _) = useState(Option.empty[User])
  val (loading, setLoading, _) = useState(true)
  val (error, setError, _) = useState(Option.empty[String])
  
  useEffect(() => {
    setLoading(true)
    setError(None)
    
    // Fetch user data
    fetchUser(props.userId)
      .map(userData => {
        setUser(Some(userData))
        setLoading(false)
      })
      .recover {
        case e: Exception =>
          setError(Some(e.getMessage))
          setLoading(false)
      }
  }, Seq(props.userId))
  
  div(
    if (loading) {
      div("Loading...")
    } else if (error.isDefined) {
      div(s"Error: ${error.get}")
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

### Subscriptions

```scala
val ChatRoom = (props: ChatRoomProps) => {
  val (messages, setMessages, updateMessages) = useState(Vector.empty[Message])
  
  useEffect(() => {
    // Subscribe to chat room
    val subscription = chatService.subscribe(props.roomId, message => {
      updateMessages(prev => prev :+ message)
    })
    
    // Cleanup: unsubscribe when component unmounts or roomId changes
    () => subscription.unsubscribe()
  }, Seq(props.roomId))
  
  // ...
}
```

### DOM Manipulation with Refs

```scala
val AutoFocusInput = () => {
  val inputRef = useRef[dom.html.Input]()
  
  useEffect(() => {
    // Focus the input element after mounting
    inputRef.current.focus()
  }, Seq())
  
  input(
    ref := inputRef,
    typ := "text",
    placeholder := "This input will be focused"
  )
}
```

### Timers

```scala
val Timer = () => {
  val (seconds, setSeconds, _) = useState(0)
  
  useEffect(() => {
    val interval = dom.window.setInterval(() => {
      setSeconds(seconds + 1)
    }, 1000)
    
    // Cleanup: clear the interval when unmounting
    () => dom.window.clearInterval(interval)
  }, Seq())
  
  div(s"Timer: $seconds seconds")
}
```

## Common Patterns

### Skipping Effects

Sometimes you want to run an effect conditionally. While you can't put `useEffect` inside a conditional, you can put the conditional logic inside the effect:

```scala
useEffect(() => {
  if (shouldRun) {
    // Only run the effect if shouldRun is true
    doSomething()
  }
}, Seq(shouldRun))
```

### Separating Concerns

If you have multiple unrelated effects, split them into separate `useEffect` calls:

```scala
val UserDashboard = () => {
  // Track page views
  useEffect(() => {
    analytics.trackPageView("dashboard")
  }, Seq())
  
  // Fetch user data
  useEffect(() => {
    fetchUserData()
  }, Seq())
  
  // Set up event listeners
  useEffect(() => {
    // Event listeners setup and cleanup
  }, Seq())
  
  // ...
}
```

This makes your code more maintainable by keeping related logic together.

## Implementation Details

In Fluxus, `useEffect` is implemented as a hook that registers an effect function and its dependencies. After the component renders and the DOM is updated, Fluxus runs all the effects whose dependencies have changed.

Effects are run in order from parent to child components, and cleanup functions are run in reverse order to ensure a consistent data flow.