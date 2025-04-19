# useSignal Hook

The `useSignal` hook integrates Fluxus with [Airstream](https://github.com/raquo/Airstream), a Functional Reactive Programming (FRP) library for Scala.js. This allows you to manage state outside of the component tree and share it between components.

## Basic Usage

```scala
import com.raquo.airstream.state.Var

// Create a signal outside of components
val countSignal = Var(0)

val Counter = () => {
  // Connect the component to the signal
  val count = useSignal(countSignal)
  
  div(
    p(s"Count: $count"),
    button(
      onClick := (() => countSignal.update(_ + 1)),
      "Increment"
    )
  )
}
```

Where:
- `countSignal` is an Airstream `Var` (a read-write signal)
- `count` is the current value of the signal, which will update whenever the signal changes
- Changes to the signal trigger re-renders of any component using `useSignal` with that signal

## Why Use Signals?

Signals offer several advantages for state management:

1. **Shared State**: Multiple components can subscribe to the same signal
2. **Derived State**: Signals can be transformed and combined
3. **Separation of Concerns**: State logic can be separated from UI components
4. **Reactive Updates**: Components automatically update when signals change
5. **Testability**: Signal-based state is easy to test independently

## Examples

### Shared Counter

```scala
import com.raquo.airstream.state.Var

// Shared state that lives outside components
object CounterState {
  val count = Var(0)
  
  def increment(): Unit = count.update(_ + 1)
  def decrement(): Unit = count.update(_ - 1)
  def reset(): Unit = count.set(0)
}

// Display component
val CountDisplay = () => {
  val count = useSignal(CounterState.count)
  
  div(cls := "counter-display", s"Count: $count")
}

// Controls component
val CountControls = () => {
  div(
    cls := "counter-controls",
    button(
      onClick := (() => CounterState.decrement()),
      "−"
    ),
    button(
      onClick := (() => CounterState.reset()),
      "Reset"
    ),
    button(
      onClick := (() => CounterState.increment()),
      "+"
    )
  )
}

// Parent component that uses both
val CounterApp = () => {
  div(
    cls := "counter-app",
    h1("Shared Counter"),
    CountDisplay <> (),
    CountDisplay <> (), // Multiple instances share the same state
    CountControls <> ()
  )
}
```

### Derived Signals

```scala
import com.raquo.airstream.state.Var
import com.raquo.airstream.core.Transaction

object CartState {
  case class Item(id: String, name: String, price: Double, quantity: Int)
  
  val items = Var(Vector.empty[Item])
  
  // Derived signals
  val totalItems = items.signal.map(_.map(_.quantity).sum)
  val subtotal = items.signal.map(_.map(item => item.price * item.quantity).sum)
  val tax = subtotal.map(_ * 0.07) // 7% tax
  val total = subtotal.combineWith(tax)(_ + _)
  
  def addItem(item: Item): Unit = {
    Transaction { _ =>
      items.update { currentItems =>
        currentItems.find(_.id == item.id) match {
          case Some(_) => 
            // Item exists, increment quantity
            currentItems.map { i =>
              if (i.id == item.id) i.copy(quantity = i.quantity + 1)
              else i
            }
          case None => 
            // New item
            currentItems :+ item
        }
      }
    }
  }
  
  def removeItem(id: String): Unit = {
    items.update(_.filterNot(_.id == id))
  }
  
  def updateQuantity(id: String, quantity: Int): Unit = {
    if (quantity <= 0) {
      removeItem(id)
    } else {
      items.update(_.map { item =>
        if (item.id == id) item.copy(quantity = quantity)
        else item
      })
    }
  }
}

val ShoppingCart = () => {
  val items = useSignal(CartState.items)
  val subtotal = useSignal(CartState.subtotal)
  val tax = useSignal(CartState.tax)
  val total = useSignal(CartState.total)
  
  div(
    cls := "shopping-cart",
    h2("Shopping Cart"),
    
    // Item list
    if (items.isEmpty) {
      p("Your cart is empty")
    } else {
      div(
        cls := "cart-items",
        items.map { item =>
          div(
            key := item.id,
            cls := "cart-item",
            div(cls := "item-name", item.name),
            div(cls := "item-price", f"$${item.price}%.2f"),
            div(
              cls := "item-quantity",
              button(
                onClick := (() => CartState.updateQuantity(item.id, item.quantity - 1)),
                "−"
              ),
              span(item.quantity.toString),
              button(
                onClick := (() => CartState.updateQuantity(item.id, item.quantity + 1)),
                "+"
              )
            ),
            div(
              cls := "item-total",
              f"$${item.price * item.quantity}%.2f"
            ),
            button(
              cls := "remove-item",
              onClick := (() => CartState.removeItem(item.id)),
              "×"
            )
          )
        }
      )
    },
    
    // Cart summary
    div(
      cls := "cart-summary",
      div(
        cls := "summary-row",
        span("Subtotal:"),
        span(f"$$$subtotal%.2f")
      ),
      div(
        cls := "summary-row",
        span("Tax:"),
        span(f"$$$tax%.2f")
      ),
      div(
        cls := "summary-row total",
        span("Total:"),
        span(f"$$$total%.2f")
      )
    )
  )
}

val ProductCatalog = () => {
  val products = Vector(
    CartState.Item("p1", "T-Shirt", 19.99, 1),
    CartState.Item("p2", "Jeans", 49.99, 1),
    CartState.Item("p3", "Shoes", 79.99, 1)
  )
  
  div(
    cls := "product-catalog",
    h2("Products"),
    div(
      cls := "products",
      products.map { product =>
        div(
          key := product.id,
          cls := "product",
          div(cls := "product-name", product.name),
          div(cls := "product-price", f"$${product.price}%.2f"),
          button(
            onClick := (() => CartState.addItem(product)),
            "Add to Cart"
          )
        )
      }
    )
  )
}
```

### Form State Management

```scala
import com.raquo.airstream.state.Var
import com.raquo.airstream.core.Transaction

case class FormData(
  username: String = "",
  email: String = "",
  password: String = ""
)

case class FormErrors(
  username: Option[String] = None,
  email: Option[String] = None,
  password: Option[String] = None
)

object FormState {
  val data = Var(FormData())
  val errors = Var(FormErrors())
  val isSubmitting = Var(false)
  
  // Derived signals
  val isValid = data.signal.map { d =>
    d.username.nonEmpty && 
    d.email.nonEmpty && d.email.contains("@") &&
    d.password.length >= 8
  }
  
  def setUsername(value: String): Unit = {
    Transaction { _ =>
      data.update(_.copy(username = value))
      validateUsername()
    }
  }
  
  def setEmail(value: String): Unit = {
    Transaction { _ =>
      data.update(_.copy(email = value))
      validateEmail()
    }
  }
  
  def setPassword(value: String): Unit = {
    Transaction { _ =>
      data.update(_.copy(password = value))
      validatePassword()
    }
  }
  
  private def validateUsername(): Unit = {
    val usernameError = if (data.now().username.isEmpty) {
      Some("Username is required")
    } else {
      None
    }
    
    errors.update(_.copy(username = usernameError))
  }
  
  private def validateEmail(): Unit = {
    val emailError = if (data.now().email.isEmpty) {
      Some("Email is required")
    } else if (!data.now().email.contains("@")) {
      Some("Invalid email format")
    } else {
      None
    }
    
    errors.update(_.copy(email = emailError))
  }
  
  private def validatePassword(): Unit = {
    val passwordError = if (data.now().password.isEmpty) {
      Some("Password is required")
    } else if (data.now().password.length < 8) {
      Some("Password must be at least 8 characters")
    } else {
      None
    }
    
    errors.update(_.copy(password = passwordError))
  }
  
  def validateAll(): Boolean = {
    validateUsername()
    validateEmail()
    validatePassword()
    
    errors.now().username.isEmpty && 
    errors.now().email.isEmpty && 
    errors.now().password.isEmpty
  }
  
  def submitForm(): Unit = {
    if (validateAll()) {
      isSubmitting.set(true)
      
      // Simulate API call
      js.timers.setTimeout(2000) {
        isSubmitting.set(false)
        // Reset form on success
        data.set(FormData())
      }
    }
  }
}

val SignupForm = () => {
  val data = useSignal(FormState.data)
  val errors = useSignal(FormState.errors)
  val isValid = useSignal(FormState.isValid)
  val isSubmitting = useSignal(FormState.isSubmitting)
  
  def handleSubmit(e: dom.Event): Unit = {
    e.preventDefault()
    FormState.submitForm()
  }
  
  form(
    onSubmit := handleSubmit,
    
    div(
      cls := "form-group",
      label("Username"),
      input(
        typ := "text",
        value := data.username,
        onInput := ((e: dom.Event) => 
          FormState.setUsername(e.target.asInstanceOf[dom.html.Input].value)
        )
      ),
      errors.username.map(error => 
        div(cls := "error", error)
      )
    ),
    
    div(
      cls := "form-group",
      label("Email"),
      input(
        typ := "email",
        value := data.email,
        onInput := ((e: dom.Event) => 
          FormState.setEmail(e.target.asInstanceOf[dom.html.Input].value)
        )
      ),
      errors.email.map(error => 
        div(cls := "error", error)
      )
    ),
    
    div(
      cls := "form-group",
      label("Password"),
      input(
        typ := "password",
        value := data.password,
        onInput := ((e: dom.Event) => 
          FormState.setPassword(e.target.asInstanceOf[dom.html.Input].value)
        )
      ),
      errors.password.map(error => 
        div(cls := "error", error)
      )
    ),
    
    button(
      typ := "submit",
      disabled := (!isValid || isSubmitting),
      if (isSubmitting) "Signing up..." else "Sign Up"
    )
  )
}
```

## Signal Composition

Airstream provides powerful operators for working with signals:

### Transforming Signals

```scala
// Map a signal to a different type
val count = Var(0)
val doubledCount = count.signal.map(_ * 2)
val isEven = count.signal.map(_ % 2 == 0)
val countString = count.signal.map(_.toString)

// Use in a component
val DisplayComponent = () => {
  val doubled = useSignal(doubledCount)
  val even = useSignal(isEven)
  
  div(
    p(s"Doubled value: $doubled"),
    p(s"Is even: $even")
  )
}
```

### Combining Signals

```scala
val firstName = Var("John")
val lastName = Var("Doe")

// Combine two signals
val fullName = firstName.signal.combineWith(lastName.signal)((first, last) => s"$first $last")

// Use in a component
val GreetingComponent = () => {
  val name = useSignal(fullName)
  
  div(s"Hello, $name!")
}
```

### Signal Filtering

```scala
val value = Var(0)

// Only emit even values
val evenValues = value.signal.filter(_ % 2 == 0)

// Only emit when value changes
val distinctValues = value.signal.distinct

// Skip the first N values
val afterThreeValues = value.signal.skip(3)
```

## Handling Side Effects

You can perform side effects when signals change:

```scala
val authToken = Var(Option.empty[String])

// Log to console when token changes
authToken.signal.foreach { token =>
  console.log(s"Token changed: $token")
}

// Save to localStorage when token changes
authToken.signal.foreach { token =>
  token match {
    case Some(t) => dom.window.localStorage.setItem("token", t)
    case None => dom.window.localStorage.removeItem("token")
  }
}
```

## Implementation Details

The `useSignal` hook works by:

1. Setting up an Airstream observer that calls the component's state setter when the signal changes
2. Cleaning up the observer when the component unmounts
3. Subscribing to the signal only once during the component's lifecycle

Here's a simplified implementation:

```scala
def useSignal[A](signal: Var[A]): A = {
  // Initialize Airstream's transaction system if needed
  SignalHook

  // Use state to trigger re-renders when the signal changes
  val (value, setValue, _) = useState[A](signal.now())

  useEffect(
    () => {
      val owner = new EffectOwner()
      val observer = Observer[A](setValue)

      // Observe the signal with the observer
      val subscription = signal.signal.addObserver(observer)(owner)

      // Cleanup on unmount or signal change
      () => {
        Transaction { _ =>
          subscription.kill()
          owner.cleanup()
        }
      }
    },
    Seq(signal), // Only re-subscribe if the signal reference changes
  )

  value
}
```

## Best Practices

### Signal Ownership

Signals should typically be owned by a module or service, not by components:

```scala
// Good: Signal owned by a module
object UserStore {
  val currentUser = Var(Option.empty[User])
  
  def login(username: String, password: String): Future[User] = {
    // Implementation
  }
  
  def logout(): Unit = {
    currentUser.set(None)
  }
}

// Bad: Signal created inside component
val UserProfile = () => {
  val userSignal = Var(Option.empty[User]) // Don't do this!
  
  // Component body
}
```

### Separating State Logic

Use signals to separate state management logic from UI components:

```scala
// State logic
object CounterLogic {
  val count = Var(0)
  
  def increment(): Unit = count.update(_ + 1)
  def decrement(): Unit = count.update(_ - 1)
  def reset(): Unit = count.set(0)
}

// UI component
val Counter = () => {
  val count = useSignal(CounterLogic.count)
  
  div(
    p(s"Count: $count"),
    button(onClick := (() => CounterLogic.decrement()), "−"),
    button(onClick := (() => CounterLogic.reset()), "Reset"),
    button(onClick := (() => CounterLogic.increment()), "+")
  )
}
```

### Combining with useEffect

You may need to use `useEffect` with signals for more complex interactions:

```scala
val SearchComponent = () => {
  val query = useSignal(SearchStore.query)
  val results = useSignal(SearchStore.results)
  
  // Run a search when query changes
  useEffect(
    () => {
      if (query.nonEmpty) {
        SearchStore.performSearch()
      }
    },
    Seq(query) // Depend on the query value
  )
  
  // Component body
}
```

### Type Safety

Signals inherit Scala's type system, providing compile-time safety:

```scala
// Type-safe signals
val nameSignal = Var("John")
val ageSignal = Var(30)

// Won't compile: type mismatch
nameSignal.set(42)
ageSignal.set("Forty") 
```

## Performance Considerations

- **Minimize Signal Updates**: Each signal update potentially causes re-renders
- **Granular Signals**: Use multiple small signals rather than one large one
- **Derived Signals**: Use `.map`, `.combineWith`, etc. instead of deriving values in components
- **Fine-grained Subscriptions**: Subscribe only to the signals you need

## Comparison with useState

| Feature | useSignal | useState |
|---------|-----------|----------|
| Scope | Global or local | Component-local only |
| Sharing | Easily shared between components | Requires prop drilling or context |
| Updates | Any code with access can update | Only the component can update |
| Composition | Rich composition API | Limited composition |
| Update Tracking | Reactive (automatic) | Manual |
| Side Effects | Can trigger side effects directly | Requires useEffect |
| Memory Management | Requires explicit cleanup | Automatic |

## When to Use useSignal vs. useState

Use **useSignal** when:
- State needs to be shared between components
- State logic should be separated from UI
- You need derived/computed state
- You need to react to state changes outside components

Use **useState** when:
- State is truly local to a component
- Component state is simple
- You want automatic cleanup
- You don't need to share state

Often the best approach is to combine both: use signals for shared application state and useState for component-local state.