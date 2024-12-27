# Fluxus Framework User Guide

## Key Management

### Understanding Keys
Keys are unique identifiers that help Fluxus track which components are which when rendering lists of elements. They help maintain component state and prevent unnecessary re-renders.

### When to Use Keys
- **Required**: When rendering lists of elements
- **Optional**: For static elements
- **Never**: Generated during render (like array indices)

### Good Key Usage
```scala
// Good - Using stable, unique IDs
todos.map(todo => 
  createElement("li", Props.empty, Some(todo.id), 
    TextNode(todo.text)
  )
)

// Good - Using unique business identifiers
users.map(user => 
  UserComponent(UserProps(user), Some(user.email))
)
```

### Bad Key Usage
```scala
// Bad - Using array index
items.zipWithIndex.map { case (item, i) =>
  createElement("div", Props.empty, Some(i.toString))
}

// Bad - Generated during render
items.map(item =>
  createElement("div", Props.empty, Some(Random.nextString(5)))
)

// Bad - Non-unique keys
items.map(item =>
  createElement("div", Props.empty, Some("row"))
)
```

### Key Rules
1. Keys must be unique among siblings
2. Keys should be stable across renders
3. Keys should be tied to data identity
4. Avoid using array indices as keys
5. Keys are scoped to their parent element

### Common Patterns
1. Using Database IDs
   ```scala
   dbItems.map(item => 
     createElement("div", Props.empty, Some(item.id.toString))
   )
   ```

2. Using Natural Identifiers
   ```scala
   contacts.map(contact => 
     createElement("div", Props.empty, Some(contact.email))
   )
   ```

3. Using Composite Keys
   ```scala
   items.map(item => 
     createElement("div", Props.empty, Some(s"${item.type}-${item.id}"))
   )
   ```

### Impact on Performance
- Proper key usage helps Fluxus efficiently update lists
- Improper keys can cause unnecessary re-renders
- Missing keys in lists forces full re-renders
- Random or unstable keys defeat the purpose of the key system

### Debugging Key Issues
- Framework logs will show warnings for duplicate keys
- Missing keys on list items will generate warnings
- Component state issues often trace back to key problems
- Watch for unexpected component remounts