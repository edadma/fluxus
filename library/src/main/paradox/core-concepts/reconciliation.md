# Reconciliation

Reconciliation is the process of efficiently updating the DOM based on changes to the virtual DOM. It's a core part of what makes Fluxus performant.

## How Reconciliation Works

When a component's state or props change, Fluxus performs these steps:

1. The component function is called again to produce a new virtual DOM tree
2. This new tree is compared with the previous tree (diffing)
3. A minimal set of operations is generated to transform the real DOM
4. These operations are applied to the DOM in a single batch

## The Diff Algorithm

The diffing algorithm in Fluxus is designed to be efficient with a few key assumptions:

1. **Different node types will produce different trees**: If an element changes from a `div` to a `span`, Fluxus rebuilds the entire subtree rather than trying to patch it.

2. **Stable elements can be identified with keys**: When lists of elements change, keys help identify which items were added, removed, or moved.

3. **Children are diffed recursively**: The algorithm walks the tree recursively, comparing node by node.

## DOM Operations

The reconciliation process generates a sequence of operations that are batched together for efficiency:

- `UpdateProperties`: Updates properties on an existing element
- `ReplaceNode`: Replaces an entire node (and its children) with a new one
- `UpdateText`: Updates the content of a text node
- `RemoveProps`: Removes properties from an element
- `AddProps`: Adds new properties to an element
- `RemoveEvent`: Removes an event handler
- `AddEvent`: Adds an event handler
- `RemoveNode`: Removes a node from the DOM
- `InsertNode`: Inserts a new node
- `RerenderComponent`: Re-renders a component with new props
- `MoveNode`: Moves a node to a new position

## Key-Based Reconciliation

When rendering lists of elements, using the `key` property helps Fluxus identify which items have changed:

```scala
ul(
  items.map(item => 
    li(key := item.id, item.text)
  )
)
```

Without keys, Fluxus has to rebuild large portions of the DOM when items change position. With keys, it can identify moved elements and reuse DOM nodes, resulting in better performance.

## Component Reconciliation

Components are reconciled based on their type and key:

1. If a component's type changes (different component function), the entire subtree is replaced
2. If a component's type stays the same but props change, the component instance is reused but re-rendered
3. If both type and props are unchanged, the component can often skip re-rendering

## Batching Updates

Fluxus batches multiple state updates that occur within the same event loop iteration. This means multiple calls to state setters will result in only one re-render:

```scala
val (count, setCount, _) = useState(0)

// These will be batched into a single update
button(
  onClick := (() => {
    setCount(count + 1)
    setCount(count + 2)
  })
)
```

However, functional updates will apply sequentially:

```scala
val (count, _, updateCount) = useState(0)

// These will apply sequentially, resulting in count = 2
button(
  onClick := (() => {
    updateCount(c => c + 1)
    updateCount(c => c + 1)
  })
)
```

## Effect Handling

After the DOM has been updated, Fluxus runs effects (from `useEffect` hooks). This ensures that effects always see the latest DOM state.

Effects are executed in order from parent to child components, to ensure a consistent data flow.

## Implementation Details

The reconciliation process is implemented primarily in these modules:

- `diff.scala`: Generates operations by comparing virtual DOM trees
- `commit.scala`: Applies operations to the real DOM
- `BatchScheduler.scala`: Handles batching updates and scheduling effects
- `reconcile.scala`: Coordinates the overall reconciliation process