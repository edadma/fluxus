# Fluxus Project Goals

## Overview

Create a minimalist React-like UI framework in Scala.js that implements core React concepts in their simplest form, prioritizing correctness and clarity over optimization. The framework should handle nested component structures properly while maintaining a predictable and understandable execution model.

## Core Features

### Components
- Support functional components
- Handle parent/child component relationships
- Support nested component trees of arbitrary depth
- Allow components to maintain state
- Support rendering lists of components (with simple index-based diffing)

### State Management
- Allow components to maintain and update state
- Handle state changes synchronously and completely
- Each state change triggers a complete re-render cycle
- No batching of updates (each update processes fully before the next begins)

### Virtual DOM
- Maintain a virtual DOM tree
- Support both element nodes (div, span, etc.) and component nodes
- Perform diffing to determine necessary DOM updates
- Handle nested structures correctly
- Support basic list rendering without key optimization

### Effects
- Support effect hooks similar to React's useEffect
- Run effects after DOM updates are complete
- Handle effect cleanup properly
- Maintain correct ordering of effects in nested components

## Execution Model

### State Change Flow
1. State update occurs in a component
2. Component re-renders
3. Virtual DOM diffing occurs
4. DOM updates are made
5. Effects are run

### Nested Component Handling
1. Parent state changes can trigger child re-renders
2. Each state change completes fully before handling the next
3. Effects run in a predictable order based on component hierarchy

## Non-Goals (Initial Version)

These features are explicitly not included in the initial version to maintain simplicity:
- Batching of state updates
- Key-based list optimization
- Performance optimizations
- Error boundaries
- Context system
- Memoization
- Concurrent rendering

## Implementation Principles

1. Correctness Over Performance
  - Focus on making things work correctly first
  - Accept performance trade-offs for simpler implementation
  - Maintain predictable behavior

2. Clear Mental Model
  - Each operation should have clear beginning and end
  - State changes process one at a time
  - Effects have clear execution timing

3. Proper Nesting Support
  - Handle deeply nested component structures
  - Maintain proper parent/child relationships
  - Support arbitrary nesting depth

## Success Criteria

The framework should:
1. Handle nested components correctly
2. Process state changes predictably
3. Run effects at the right time
4. Support basic list rendering
5. Maintain correct DOM state
6. Handle multiple timer-based updates correctly

## Example Use Cases to Support

1. Basic Counter Component
  - Single component with state
  - Button triggers state update
  - DOM updates reflect state changes

2. Nested Components
  - Parent component with state
  - Multiple child components
  - State changes affect children

3. Timer Components
  - Multiple independent timers
  - Each timer maintains own state
  - Updates don't interfere with each other

4. List Rendering
  - Basic list of items
  - Add/remove items
  - Update individual items

5. Effect Handling
  - Setup/cleanup of resources
  - Effects that depend on state
  - Effects in nested components

## Testing Focus

1. Component Lifecycle
  - Creation
  - State updates
  - Effect execution
  - Cleanup

2. Nested Structures
  - Parent/child rendering
  - Effect ordering
  - State change propagation

3. Multiple Updates
  - Timer-based updates
  - Multiple state changes
  - Effect execution order

4. DOM Correctness
  - Proper DOM updates
  - List rendering
  - Nested structure updates

## Future Expansion Possibilities

While not included in the initial version, the architecture should be designed such that these features could be added later without major restructuring:
- Key handling for lists
- Batched updates
- Performance optimizations
- Error boundaries
- Context system