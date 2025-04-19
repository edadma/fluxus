# Core Concepts

This section explains the fundamental concepts and architecture of Fluxus.

@@@ index

* [Virtual DOM](virtual-dom.md)
* [Components](components.md)
* [Reconciliation](reconciliation.md)
* [Hooks](hooks.md)
* [Event Handling](event-handling.md)

@@@

## Overview

Fluxus is built around a few key concepts that form the foundation of the framework:

1. **Virtual DOM**: A lightweight representation of the actual DOM that allows for efficient updates.
2. **Components**: Reusable, composable UI elements that encapsulate rendering logic and state.
3. **Reconciliation**: The process of efficiently updating the DOM based on changes to the virtual DOM.
4. **Hooks**: Functions that let you "hook into" component state and lifecycle features.
5. **Event Handling**: A system for handling user interactions and other events in a type-safe manner.

### Architecture

At a high level, Fluxus follows this workflow:

1. Component functions are called to produce a virtual DOM tree
2. The virtual DOM is compared with the previous version (reconciliation)
3. Only the minimal set of changes needed is applied to the real DOM
4. Side effects (via hooks) are executed after the DOM is updated

This architecture provides several benefits:

- **Declarative UI**: You describe how your UI should look for a given state, and Fluxus handles the updates.
- **Component-Based**: Build encapsulated components that manage their own state.
- **Efficient Updates**: The reconciliation process ensures minimal DOM operations.
- **Type Safety**: Leverage Scala's type system throughout your UI code.

### Mental Model

When working with Fluxus, it helps to think of your UI as a function of state:

```
UI = f(state)
```

Where:
- **state** is the data that drives your application
- **f** is your component hierarchy that transforms state into UI elements
- **UI** is the visual representation shown to the user

When state changes (through hooks or props), your components are re-executed to produce a new virtual DOM, which is then reconciled with the previous version to update the real DOM.

This model leads to a more predictable and maintainable codebase, as UI changes are always tied directly to state changes.