# Virtual DOM

The Virtual DOM is a core concept in Fluxus that enables efficient UI updates.

## What is the Virtual DOM?

The Virtual DOM is a lightweight in-memory representation of the actual DOM. In Fluxus, it's implemented as a tree of `FluxusNode` objects that describe what the UI should look like.

Unlike the real DOM, which is expensive to manipulate, the Virtual DOM can be created and modified quickly in memory. This allows Fluxus to:

1. Calculate the minimal set of changes needed to update the real DOM
2. Batch multiple updates together for better performance
3. Provide a more declarative API for building UIs

## How It Works

The Virtual DOM system in Fluxus follows this process:

1. **Render**: Component functions are called to produce a tree of `FluxusNode` objects
2. **Diff**: The new tree is compared with the previous one to determine what changed
3. **Patch**: The necessary changes are applied to the real DOM

## FluxusNode Hierarchy

In Fluxus, the Virtual DOM is represented by the sealed trait `FluxusNode`. The main node types are:

### ElementNode

Represents an HTML or SVG element:

```scala
case class ElementNode(
  tag: String,
  attrs: Map[String, Any],
  events: Map[String, js.Function1[dom.Event, Unit]],
  children: Vector[FluxusNode],
  parent: Option[FluxusNode],
  domNode: Option[Node],
  namespace: Option[String] = None,
  ref: Option[RefHook] = None,
  key: Option[String] = None
) extends FluxusNode
```

### TextNode

Represents a text node in the DOM:

```scala
case class TextNode(
  text: String,
  parent: Option[FluxusNode],
  domNode: Option[Node]
) extends FluxusNode
```

### ComponentNode

Represents a user-defined component:

```scala
case class ComponentNode(
  component: Product => FluxusNode,
  props: Product,
  parent: Option[FluxusNode] = None,
  domNode: Option[Node] = None,
  key: Option[String] = None,
  instance: Option[ComponentInstance] = None,
  componentHash: Int = 0
) extends FluxusNode
```

### RawNode

Represents a direct reference to an existing DOM element:

```scala
case class RawNode(
  element: dom.Element,
  parent: Option[FluxusNode] = None,
  domNode: Option[Node] = None,
  key: Option[String] = None
) extends FluxusNode
```

### EmptyNode

Represents the absence of a node (like `null` in React):

```scala
case object EmptyNode extends FluxusNode
```

## Creating Virtual DOM Nodes

Fluxus provides a set of helper functions to create Virtual DOM nodes:

```scala
// Creating element nodes
div(cls := "container", "Hello, world!")

// Creating nested structures
div(
  cls := "card",
  h1(cls := "title", "Card Title"),
  p(cls := "content", "Card content goes here")
)

// Conditional rendering
div(
  if (isLoggedIn) 
    button(onClick := logout, "Log out")
  else 
    button(onClick := login, "Log in")
)
```

## Keys

When rendering lists of elements, it's often important to provide a unique `key` for each item:

```scala
ul(
  items.map(item => 
    li(key := item.id, item.name)
  )
)
```

Keys help the reconciliation algorithm identify which items have changed, moved, or been removed, leading to more efficient updates.

## Benefits of Virtual DOM

The Virtual DOM approach provides several benefits:

1. **Performance**: By minimizing actual DOM operations, UI updates are faster
2. **Simplicity**: Developers can describe UIs declaratively without worrying about DOM manipulation
3. **Predictability**: UI rendering is a pure function of state, making behavior more predictable
4. **Testability**: Virtual DOM trees can be tested without a browser environment