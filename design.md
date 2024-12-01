# 1. CORE ARCHITECTURE

## Overview
The Fluxus Framework implements a lightweight React-like UI framework for Scala.js, focusing on functional components with hooks and virtual DOM diffing.

## Type System

### FluxusNode
Base type for the virtual DOM with three variants:

1. ElementNode
```
ElementNode represents DOM elements:
- tag: HTML/SVG tag name
- props: Component properties
- events: Map of event handlers
- children: Array of child nodes
- parent: Reference to parent node
- domNode: Reference to actual DOM node
- key: Optional identifier for reconciliation
- namespace: For SVG/other namespace support
- ref: Optional reference object

Invariants:
- tag must be valid HTML/SVG tag
- parent must be ElementNode or ComponentNode
- children must not contain cycles
- ref callback must not trigger state updates
```

2. ComponentNode
```
ComponentNode represents user components:
- component: Reference to component function
- props: Component properties
- instance: Reference to component instance
- key: Optional identifier
- lazy: Flag for lazy loading
- suspense: Flag for suspense boundary
- errorBoundary: Flag for error handling

Invariants:
- component must be valid function
- props must be immutable
- instance must be null until mounted
- lazy/suspense/errorBoundary are mutually exclusive
```

3. TextNode
```
TextNode represents text content:
- text: The actual text content
- parent: Reference to parent node
- domNode: Reference to DOM text node
- key: Optional identifier

Invariants:
- text must not be null
- parent must be ElementNode
```

### ComponentInstance
Manages component state and lifecycle:
```
ComponentInstance tracks:
- Core Identity:
  * id: Unique identifier (UUID v4)
  * component: Reference to component function
  * componentType: String identifier
  * props: Current properties
  * state: Component state
  * stateVersion: Incremental state version counter

- Timing and Metrics:
  * createTime: Instance creation timestamp
  * lastUpdateTime: Last update timestamp
  * updateCount: Number of renders
  * lastRenderDuration: Milliseconds taken for last render
  * totalRenderTime: Cumulative render time

- Structure:
  * parent: Parent instance reference
  * children: Set of child instances
  * depth: Tree depth level
  * rendered: Current rendered virtual DOM
  * renderVersion: Incremental render version counter

- State Management:
  * isRendering: Flag for render phase
  * needsRender: Flag for pending renders
  * isSuspended: Suspense state
  * hooks: Array of hook states
  * hookIndex: Current hook position
  * effects: Array of effects
  * effectVersion: Incremental effect version counter
  * hasEffectError: Effect error state
  * isInCleanup: Cleanup phase flag
  * preserveState: State preservation flag

- Error Handling:
  * errorState: Current error information
  * errorBoundaryStack: Stack of error boundaries
  * lastError: Most recent error
  * errorCount: Total error count

- References:
  * context: Context values map
  * refs: Reference objects map
  * domNode: Actual DOM node reference
  * debugName: Identifier for debugging

State Preservation Rules:
1. State preserved when key remains constant
2. State reset on component type change
3. State preserved during suspense
4. State reset on explicit unmount
5. Hook state follows component state rules

Invariants:
- id must be unique across all instances
- depth must match actual tree depth
- errorCount must match error history length
- hooks must be called in consistent order
```

## Render Context
Manages the rendering process and component stack:
```
RenderContext maintains:
- Component stack tracking:
  * instanceStack: Current render stack
  * effectStack: Pending effects
  * errorBoundaryStack: Error boundaries
  * suspenseStack: Suspense boundaries
  * phaseStack: Stack of render phases
  * resourceMap: Active resource tracking

- Phase management:
  * currentPhase: Current operation phase
  * batchUpdateDepth: Batch update nesting
  * renderStartTime: Current render start timestamp
  * lastPhaseChange: Last phase transition time
  * phaseTimeout: Phase-specific timeout value

- Resource monitoring:
  * activeTimers: Set of active timer IDs
  * pendingEffects: Queue of effects to run
  * resourceWarnings: Array of threshold warnings
  * memoryUsage: Current memory metrics

Invariants:
- instanceStack depth <= maxTreeDepth
- batchUpdateDepth >= 0
- phaseStack never empty during render
- currentPhase matches phaseStack top
```

## Framework Configuration
```
FrameworkConfig controls:
- Debug Options:
  * debugMode: Enable debug features
  * verboseLogging: Detailed logging
  * trackResourceMetrics: Resource tracking
  * preserveErrorStack: Keep full error stacks
  * validateProps: Runtime props validation
  * validateHookCalls: Verify hook call order
  * trackRenderTiming: Measure render durations

- Performance Settings:
  * batchUpdates: Enable update batching
  * asyncRendering: Async render support
  * maxUpdateDepth: Maximum update nesting
  * renderChunkSize: Max nodes per render chunk
  * deferTimeout: Deferred update timeout
  * throttleUpdates: Update rate limiting
  * memoizeProps: Props equality optimization

- Feature Flags:
  * enableSuspense: Suspense support
  * enableErrorBoundaries: Error boundary support
  * enableStrictMode: Strict mode checks
  * enableLazy: Lazy component loading
  * enableProfiling: Performance profiling
  * enableHotReload: Hot reload support
  * enableDevTools: Development tools integration

- Timeouts (milliseconds):
  * renderTimeout: Max render time (5000)
  * effectTimeout: Max effect time (2000)
  * cleanupTimeout: Max cleanup time (2000)
  * suspenseTimeout: Max suspense time (5000)
  * batchTimeout: Max batch delay (50)
  * deferredTimeout: Max defer time (100)
  * idleTimeout: Idle render slice (16)

- Resource Limits:
  * maxComponents: Component count limit (10000)
  * maxDOMNodes: DOM node limit (50000)
  * maxEventListeners: Listener limit (10000)
  * maxTreeDepth: Tree depth limit (64)
  * maxHooksPerComponent: Hook limit (100)
  * maxEffectsPerComponent: Effect limit (50)
  * maxBatchSize: Max batch updates (100)

Validation Rules:
1. All timeouts must be positive integers
2. All limits must be positive integers
3. Tree depth must be between 1 and 100
4. Batch timeout must be <= 100ms
5. Render timeout must be >= 1000ms
```

## Initial App Mount Process
```
Mount Process Steps:
1. Container Validation
   - Verify DOM mount point exists and is valid
   - Validate configuration settings
   - Check container is empty or clearable
   - Verify container is not within another Fluxus tree

2. Context Setup
   - Initialize render context
   - Begin mount phase
   - Setup performance monitoring
   - Initialize resource tracking
   - Setup error capture

3. Root Creation
   - Create root component instance with validated settings
   - Setup root error boundary
   - Initialize root suspense boundary
   - Setup development tools integration
   - Register root for garbage collection

4. Initial Render
   - Enter render phase
   - Create initial virtual DOM tree
   - Record render timing
   - Validate tree structure
   - Clear container safely
   - Create initial DOM with error recovery
   - Setup event delegation
   - Record DOM metrics

5. Finalization
   - Update resource counts
   - Initialize logging system
   - Complete mount phase
   - Verify mount invariants
   - Start idle processing
   - Enable updates

Error Recovery:
1. Partial mount cleanup
2. Container restoration
3. Resource reclamation
4. Error boundary activation
5. Development mode reporting

Resource Initialization:
1. Component registry
2. Event listener tracking
3. Timer management
4. Memory monitoring
5. Performance metrics
6. Development tools hooks
```

The architecture ensures:
- Functional component support
- Hook-based state management
- Virtual DOM reconciliation
- Efficient updates
- Comprehensive debugging
- Resource tracking
- Error resilience
- Development tooling

# 2. COMPONENT LIFECYCLE

## Creation Phase

### Component First Render
```
Creation Process:
1. Instance Creation
   - Generate unique instance ID
   - Set creation timestamp
   - Initialize component type and name
   - Validate initial props
   - Set up debug tracking

2. State Initialization
   - Create state storage
   - Initialize state version counter
   - Set up state update queue
   - Configure state batching
   - Set initial render flag

3. Effect Setup
   - Initialize effects array
   - Set up effect version tracking
   - Initialize cleanup tracking
   - Configure effect error boundaries
   - Set up effect timing metrics

4. Relationship Management
   - Set parent reference
   - Initialize children set
   - Calculate tree depth
   - Validate tree structure
   - Register with parent

5. Resource Registration
   - Log component creation
   - Update resource counters
   - Initialize memory tracking
   - Register event listeners
   - Setup garbage collection

Error Handling:
- Log detailed error context
- Capture component stack
- Create error boundary if needed
- Clean up partial initialization
- Update error metrics

Invariants:
- Instance ID must be unique
- Parent reference must be valid
- Tree depth must be correct
- Resource counts must be accurate
- No render flag must be set
```

## Update Phase

### Update Triggers
```
Update Sources:
1. Props Changes
   - Log at DEBUG level
   - Compare prop values
   - Check prop types
   - Track prop history
   - Measure update frequency

2. State Updates
   - Log at DEBUG level
   - Queue state changes
   - Batch related updates
   - Track state versions
   - Validate state changes

3. Parent Re-renders
   - Log at TRACE level
   - Check optimization flags
   - Track render chains
   - Measure propagation
   - Control cascading updates

State Preservation Rules:
1. Hook Data
   - Preserve hook order
   - Maintain hook state
   - Track dependencies
   - Handle hook errors
   - Clean up unused hooks

2. Effect Cleanups
   - Run in reverse order
   - Handle cleanup errors
   - Track cleanup timing
   - Manage resources
   - Verify completion

3. Child Components
   - Preserve instances
   - Maintain keys
   - Track relationships
   - Handle unmounts
   - Manage references

4. Component Identity
   - Maintain keys
   - Track versions
   - Handle type changes
   - Preserve context
   - Manage refs

Error Handling:
- Log error context
- Generate stack traces
- Track error history
- Update error metrics
- Trigger error boundaries
```

## Disposal Phase

### Disposal Triggers
```
Trigger Types:
1. Tree Removal
   - Cascade cleanup
   - Track removal timing
   - Handle partial failures
   - Clean references
   - Update metrics

2. Parent Unmount
   - Coordinate cleanup
   - Preserve recoverable state
   - Handle suspended state
   - Clear relationships
   - Update tree structure

3. Key Changes
   - Compare keys
   - Handle transitions
   - Preserve state if needed
   - Update identities
   - Track key history
```

### Cleanup Process
```
Standard Cleanup Order:
1. Log Disposal Start
   - Record timestamp
   - Log component info
   - Track cleanup start
   - Initialize metrics
   - Set cleanup flags

2. Dispose Children
   - Recursive cleanup
   - Track progress
   - Handle errors
   - Maintain order
   - Update relationships

3. Run Effect Cleanups
   - Reverse order execution
   - Handle failures
   - Track timing
   - Clear resources
   - Update effect state

4. Remove Event Listeners
   - Systematic removal
   - Verify cleanup
   - Update counts
   - Clear handlers
   - Track changes

5. Clear Hook Data
   - Reset hooks
   - Clean state
   - Clear dependencies
   - Update metrics
   - Handle errors

6. Clear DOM References
   - Remove nodes
   - Update virtual DOM
   - Clear refs
   - Track changes
   - Update metrics

7. Remove from Parent
   - Update relationships
   - Clear references
   - Update tree
   - Handle errors
   - Track changes

8. Update Resource Counts
   - Decrement counters
   - Update metrics
   - Verify totals
   - Track changes
   - Log updates

9. Clear References
   - Systematic clearing
   - Verify completion
   - Update tracking
   - Handle cycles
   - Log changes

10. Log Completion
    - Record timing
    - Update metrics
    - Verify cleanup
    - Track resources
    - Complete logging

Error Recovery:
1. Capture error context
2. Log cleanup failures
3. Attempt partial cleanup
4. Update error metrics
5. Notify error boundaries

Resource Management:
1. Track cleanup progress
2. Monitor resource release
3. Verify cleanup completion
4. Handle partial failures
5. Update global metrics
```

This architecture ensures:
- Reliable component management
- Consistent state handling
- Clean resource management
- Comprehensive error handling
- Accurate tracking and monitoring
- Proper cleanup sequencing
- Maintainable component trees
- Efficient update processing

