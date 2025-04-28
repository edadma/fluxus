# useRef

The `useRef` hook creates a mutable container that persists for the lifetime of the component. It's primarily used for:

1. Accessing DOM elements directly
2. Storing mutable values that don't trigger re-renders when changed
3. Keeping track of previous values or state across renders

## Basic Usage

```scala
val ref = useRef[T](initialValue)
```

Where:
- `T` is the type of value the ref will hold
- `initialValue` is the initial value for `ref.current` (defaults to `null`)
- The returned object has a single mutable property, `current`, which can be read and written to

## Accessing DOM Elements

The most common use case for `useRef` is to access DOM elements:

```scala
val InputWithFocus = () => {
  val inputRef = useRef[dom.html.Input]()
  
  def handleButtonClick(): Unit = {
    // Access the DOM element directly
    inputRef.current.focus()
  }
  
  div(
    input(
      ref := inputRef,
      typ := "text"
    ),
    button(
      onClick := (() => handleButtonClick()),
      "Focus the input"
    )
  )
}
```

## Persisting Values Between Renders

Unlike state, changing a ref's value doesn't cause a re-render:

```scala
val RenderCounter = () => {
  // This counts renders but doesn't appear in the output
  val renderCount = useRef(0)
  renderCount.current += 1
  
  val (count, setCount, _) = useState(0)
  
  div(
    p(s"Count: $count"),
    p(s"Render count: ${renderCount.current}"),
    button(
      onClick := (() => setCount(count + 1)),
      "Increment"
    )
  )
}
```

## Keeping Track of Previous Values

Refs can be used to remember the previous value of a prop or state:

```scala
def usePrevious[T](value: T): T = {
  val ref = useRef[T]()
  
  useEffect(() => {
    ref.current = value
  }, Seq(value))
  
  ref.current
}

val CounterWithPrevious = () => {
  val (count, setCount, _) = useState(0)
  val previousCount = usePrevious(count)
  
  div(
    p(s"Count: $count"),
    p(s"Previous count: ${Option(previousCount).getOrElse("none")}"),
    button(
      onClick := (() => setCount(count + 1)),
      "Increment"
    )
  )
}
```

## Avoiding Re-Renders with Mutable State

When you need to update state frequently but don't want to trigger re-renders, you can use a ref to track the current value and update the state less frequently:

```scala
val MouseTracker = () => {
  val [position, setPosition, _] = useState((0, 0))
  val positionRef = useRef((0, 0))
  
  useEffect(() => {
    const handleMouseMove = (e: dom.MouseEvent) => {
      // Update ref without causing re-render
      positionRef.current = (e.clientX, e.clientY)
    }
    
    // Update state (and re-render) every 100ms
    val interval = dom.window.setInterval(() => {
      setPosition(positionRef.current)
    }, 100)
    
    document.addEventListener("mousemove", handleMouseMove)
    
    () => {
      document.removeEventListener("mousemove", handleMouseMove)
      dom.window.clearInterval(interval)
    }
  }, Seq())
  
  div(s"Mouse position: ${position._1}, ${position._2}")
}
```

## Using with Complex DOM Elements

You can specify the exact type of DOM element you want to reference:

```scala
val VideoPlayer = () => {
  val videoRef = useRef[dom.html.Video]()
  
  def handlePlay(): Unit = {
    videoRef.current.play()
  }
  
  def handlePause(): Unit = {
    videoRef.current.pause()
  }
  
  div(
    video(
      ref := videoRef,
      width := "640",
      height := "360",
      controls := true,
      source(
        src := "video.mp4",
        typ := "video/mp4"
      )
    ),
    div(
      button(onClick := (() => handlePlay()), "Play"),
      button(onClick := (() => handlePause()), "Pause")
    )
  )
}
```

## Forwarding Refs to Custom Components

You can use `forwardRef` to pass a ref through a component to one of its children:

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
      ref := ref.asInstanceOf[RefHook & { type RefType <: dom.html.Input }],
      typ := "text",
      placeholder := props.placeholder
    )
  )
}

// Usage in a parent component
val FormWithRef = () => {
  val inputRef = useRef[dom.html.Input]()
  
  div(
    CustomInput <> (CustomInputProps("Username", "Enter username"), inputRef),
    button(
      onClick := (() => inputRef.current.focus()),
      "Focus input"
    )
  )
}
```

## Measuring DOM Elements

Refs can be used to measure DOM elements after they render:

```scala
val MeasureExample = () => {
  val divRef = useRef[dom.html.Div]()
  val (dimensions, setDimensions, _) = useState((0, 0))
  
  useEffect(() => {
    if (divRef.current != null) {
      setDimensions((divRef.current.clientWidth, divRef.current.clientHeight))
    }
  }, Seq())
  
  div(
    ref := divRef,
    cls := "measured-div",
    "This div's dimensions will be measured"
  ),
  p(s"Width: ${dimensions._1}px, Height: ${dimensions._2}px")
}
```

## Using Refs with Callbacks

You can combine refs with callbacks for more complex interactions:

```scala
val ScrollToTopButton = () => {
  val buttonRef = useRef[dom.html.Button]()
  val (isVisible, setIsVisible, _) = useState(false)
  
  useEffect(() => {
    val handleScroll = () => {
      setIsVisible(dom.window.scrollY > 100)
    }
    
    dom.window.addEventListener("scroll", handleScroll)
    
    () => dom.window.removeEventListener("scroll", handleScroll)
  }, Seq())
  
  if (isVisible) {
    button(
      ref := buttonRef,
      cls := "scroll-to-top",
      onClick := (() => dom.window.scrollTo(0, 0)),
      "↑"
    )
  } else {
    null
  }
}
```

## Implementation Details

In Fluxus, `useRef` is implemented as a hook that creates a `RefHook` object with a mutable `current` property. Unlike state hooks, updating a ref's current value doesn't trigger a re-render.

The type parameter `T` ensures type safety when accessing the `current` property, providing better development experience and preventing runtime errors.