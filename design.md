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

# 3. VIRTUAL DOM & DIFFING

## Node Types

### ElementNode
```
ElementNode represents DOM elements:
nodeType: "element"
tag: HTML/SVG tag name
props: Map of properties
events: Map of event handlers
children: Array of FluxusNodes
parent: Reference to parent node
domNode: Reference to actual DOM element
key: Optional identifier
namespace: SVG/other namespace support
ref: Reference callback

elementNode.validate():
  verify tag is valid HTML/SVG tag
  check parent is ElementNode or ComponentNode
  ensure no child reference cycles exist
  validate all props are serializable
  confirm event handlers are functions
  verify namespace matches tag type
```

### ComponentNode
```
ComponentNode represents user components:
nodeType: "component"
component: Reference to component function
props: Read-only property map
instance: ComponentInstance reference
key: Optional identifier 
lazy: Flag for lazy loading
suspense: Flag for suspense boundary
errorBoundary: Flag for error handling

componentNode.validate():
  verify component is valid function/class
  ensure props are immutable
  check instance state validity
  verify only one boundary flag is set
  validate children references
```

### TextNode
```
TextNode represents text content:
nodeType: "text"
text: String content
parent: Reference to parent ElementNode
domNode: Reference to DOM text node
key: Optional identifier

textNode.validate():
  verify text is valid string
  ensure parent is ElementNode
  check no children are present
```

## DOM Operations

### Node Creation
```
createDOMNode(vnode):
  match vnode.nodeType:
    case "element":
      element = createElementWithNamespace(vnode.tag, vnode.namespace)
      
      for each prop in vnode.props:
        if isEventHandler(prop):
          registerEventListener(element, prop, handler)
        else:
          setAttribute(element, prop, value)
      
      for each child in vnode.children:
        childNode = createDOMNode(child)
        append childNode to element
      
      trackResourceCreation("domNode")
      return element
      
    case "text":
      node = createTextNode(vnode.text)
      trackResourceCreation("textNode")
      return node
      
    case "component":
      return createDOMNode(vnode.instance.rendered)

registerEventListener(element, eventName, handler):
  create entry in listenerMap for element if not exists
  add handler to element's listener map
  attach actual DOM listener
  increment listener count
  log listener registration
```

### Node Updates
```
updateDOMNode(oldNode, newNode):
  if nodes have different types:
    replace old node with new
    return
    
  if both are text nodes:
    if text content different:
      update DOM text content
    return
    
  if both are element nodes:
    if tags different:
      replace entire node
      return
      
    updateAttributes(oldNode, newNode)
    updateEventListeners(oldNode, newNode)
    updateChildren(oldNode, newNode)

updateAttributes(oldNode, newNode):
  get old and new attribute sets
  remove attributes not in new set
  update changed attributes
  add new attributes

updateEventListeners(oldNode, newNode):
  get old and new listener maps
  remove listeners not in new map
  update changed listeners
  add new listeners
```

## Diffing Algorithm

### Core Diffing
```
diff(oldNode, newNode):
  if oldNode === newNode:
    reuse node entirely
    return
    
  if oldNode.type !== newNode.type:
    replace entire node
    return
    
  match node types:
    case TextNode:
      handleTextDiff(oldNode, newNode)
    case ElementNode:
      handleElementDiff(oldNode, newNode)
    case ComponentNode:
      handleComponentDiff(oldNode, newNode)

handleTextDiff(oldNode, newNode):
  if texts different:
    update DOM text
  preserve DOM node reference

handleElementDiff(oldNode, newNode):
  if tags different:
    replace element entirely
  else:
    updateAttributes(oldNode, newNode)
    updateEventListeners(oldNode, newNode)
    diffChildren(oldNode, newNode)

handleComponentDiff(oldNode, newNode):
  if component types different:
    handleComponentTypeChange(oldNode, newNode)
  else if needsUpdate(oldNode, newNode):
    updateComponent(oldNode, newNode)
  else:
    diffChildren(oldNode.rendered, newNode.rendered)
```

### Child List Diffing
```
diffChildren(oldParent, newParent):
  create maps of keyed children
  
  // Handle keyed children first
  for each child in newParent.children:
    if child has key:
      if matching old child exists:
        diff those nodes
      else:
        create new node
        
  // Handle non-keyed children
  for each remaining child:
    find matching old child by index
    diff if found, create if not
    
  // Remove unmatched old children
  for each old child not matched:
    remove from DOM
    clean up resources
```

### Component Type Changes
```
handleComponentTypeChange(oldNode, newNode):
  // Find preservable children
  preservableChildren = new Map()
  for each child in oldNode with key:
    add to preservableChildren map
    
  // Create new instance
  newInstance = createInstance(newNode.component)
  initialize with props
  
  // Initial render
  rendered = renderComponent(newInstance)
  
  // Preserve matching children
  for each child in rendered:
    if matching old child exists:
      transfer child to new parent
      remove from preservable map
      
  // Cleanup remaining old nodes
  cleanupFollowingStandard(oldNode)
```

This architecture provides:
- Efficient incremental updates
- Stable component handling
- Resource cleanup
- Error resilience
- Key-based child reconciliation
- Performance monitoring

# 4. KEY MANAGEMENT

### Rules
- Optional for static elements
- Required for list items
- Must be unique among siblings
- Must be stable across renders
- Log duplicate keys as ERROR

### Child List Diffing
1. Build maps of keyed nodes
2. For each new child:
    - With key: find & update/create
    - No key: position-based diff
3. Remove unmatched old nodes
4. Log key-based operations (DEBUG)

### Key Change Handling
When a node's key changes, it's treated as a completely different node. The cleanup and initialization process follows the framework's standard cleanup order defined below. This same order is used consistently throughout the framework for all cleanup operations.

1. Cleanup Order Standard:
   ```
   // Standard cleanup sequence for all cleanup operations:
   Cleanup Standard:
     1. Recursively cleanup children (depth-first, post-order traversal)
     2. Run component's effect cleanups (in reverse creation order)
     3. Remove event listeners
     4. Clear hook data
     5. Clear DOM references
     6. Remove from parent
     7. Update resource counts
     8. Clear remaining references
     
   Note: This standard order is followed by:
   - Key change cleanup (this section)
   - Component disposal (Section 2)
   - General cleanup processes (Section 6)
   - Tree cleanup operations
   - Node replacement in diffing
   ```

2. Old Component Cleanup Implementation:
   ```
   cleanupKeyedComponent(oldInstance):
     log(DEBUG, "Key change: starting cleanup")
     
     // 1. First recursively cleanup all children
     for each child in oldInstance.children:
       cleanupKeyedComponent(child)
     log(DEBUG, "Key change: child cleanup complete")
     
     // 2. Run effect cleanups in reverse order
     for each effect in reverse(oldInstance.effects):
       if effect.cleanup exists:
         effect.cleanup()
         log(DEBUG, "Key change: cleaned up effect")
     
     // 3. Remove DOM event listeners
     removeAllEventListeners(oldInstance.domNode)
     
     // 4. Clear hook data
     oldInstance.hooks = []
     oldInstance.hookIndex = 0
     log(DEBUG, "Key change: cleared hooks")
     
     // 5. Clear DOM references
     oldInstance.domNode = null
     
     // 6. Remove from parent
     if oldInstance.parent:
       oldInstance.parent.removeChild(oldInstance)
     
     // 7. Update resource tracking
     decrementResourceCounts(oldInstance)
     
     // 8. Clear remaining references
     oldInstance.parent = null
     oldInstance.rendered = null
     
     log(DEBUG, "Key change: cleanup complete")
   ```

3. New Component Initialization:
   ```
   createComponentInstance(component, props):
     instance = new ComponentInstance()
     instance.component = component
     instance.props = props
     instance.hooks = []
     instance.effects = []
     instance.hookIndex = 0
     instance.isRendering = false
     instance.needsRender = true
     instance.children = new Set()  // Added in 8th draft
     
     incrementResourceCounts(instance)
     log(DEBUG, "Created component instance")
     
     return instance
   
   initializeKeyedComponent(newInstance):
     log(DEBUG, "Key change: initializing new component")
     
     // First render will handle hook initialization
     renderComponent(newInstance)
   ```

4. Key Change Detection:
   ```
   handleKeyChange(oldNode, newNode, parent):
     if (oldNode.key !== newNode.key):
       log(DEBUG, "Key change detected", {
         old: oldNode.key, 
         new: newNode.key
       })
       
       // Clean up old component
       cleanupKeyedComponent(oldNode.instance)
       
       // Initialize new component
       newNode.instance = createComponentInstance(newNode.component, newNode.props)
       initializeKeyedComponent(newNode.instance)
       
       // Update parent references
       newNode.instance.parent = parent
   ```

# 5. HOOKS SYSTEM

### Hook Types
1. State Hook:
    - Stores value
    - Updates trigger re-render
    - Validates render safety

2. Effect Hook:
    - Manages side effects
    - Handles cleanup
    - Tracks dependencies

### useState Implementation
```
useState(initialValue):
  instance = RenderContext.currentInstance
  
  if (instance.isRendering):
    log(ERROR, "State update during render")
    throw Error("Cannot update state during render")
  
  currentHook = instance.hooks[instance.hookIndex]
  
  if first render:
    log(DEBUG, "Hook initialization")
    stateHook = {
      state: initialValue,
      setState: (newValue) => {
        if instance.isInCleanup:  // Added in 8th draft
          log(WARN, "State update during cleanup - deferring to next render")
          BatchUpdateManager.queueUpdate(instance.parent, newValue)
          return
        
        log(DEBUG, "State update", {old: stateHook.state, new: newValue})
        BatchUpdateManager.queueUpdate(instance, newValue)  // Modified in 8th draft
      }
    }
    instance.hooks[instance.hookIndex] = stateHook
  
  instance.hookIndex++
  return [currentHook.state, currentHook.setState]
```

### useEffect Implementation
```
useEffect(effectFn, deps):
  instance = RenderContext.currentInstance
  currentHook = instance.hooks[instance.hookIndex]
  
  if first render:
    log(DEBUG, "Effect initialization")
    effectHook = {
      deps: deps,
      cleanup: null
    }
    instance.hooks[instance.hookIndex] = effectHook
    instance.effects.push(() => {
      log(TRACE, "Effect execution")
      try {  // Added in 8th draft
        if (effectHook.cleanup) {
          log(TRACE, "Effect cleanup")
          runEffectCleanup(effectHook)  // Modified in 8th draft
        }
        effectHook.cleanup = effectFn()
      } catch (error) {
        log(ERROR, "Effect error", {error})
        instance.hasEffectError = true
      }
    })
  else:
    if depsChanged(oldDeps, deps):
      log(DEBUG, "Effect deps changed")
      instance.effects.push(() => {
        try {  // Added in 8th draft
          if (effectHook.cleanup) runEffectCleanup(effectHook)
          effectHook.cleanup = effectFn()
        } catch (error) {
          log(ERROR, "Effect error", {error})
          instance.hasEffectError = true
        }
      })
    currentHook.deps = deps
  
  instance.hookIndex++
```

### Effect Error Handling
```
runEffectCleanup(effect):
  if effect.cleanup:
    try:
      effect.cleanup()
    catch (error):
      log(ERROR, "Effect cleanup error", {error})
      // Continue with other cleanups despite error

runEffect(effect, instance):
  try:
    cleanup = effect.effectFn()
    if cleanup:
      effect.cleanup = cleanup
  catch (error):
    log(ERROR, "Effect execution error", {error})
    // Mark component as errored but don't block other effects
    instance.hasEffectError = true
```

# 5. HOOKS SYSTEM

### Hook Types
1. State Hook:
    - Stores value
    - Updates trigger re-render
    - Validates render safety

2. Effect Hook:
    - Manages side effects
    - Handles cleanup
    - Tracks dependencies

### useState Implementation
```
useState(initialValue):
  instance = RenderContext.currentInstance
  
  if (instance.isRendering):
    log(ERROR, "State update during render")
    throw Error("Cannot update state during render")
  
  currentHook = instance.hooks[instance.hookIndex]
  
  if first render:
    log(DEBUG, "Hook initialization")
    stateHook = {
      state: initialValue,
      setState: (newValue) => {
        if instance.isInCleanup:  // Added in 8th draft
          log(WARN, "State update during cleanup - deferring to next render")
          BatchUpdateManager.queueUpdate(instance.parent, newValue)
          return
        
        log(DEBUG, "State update", {old: stateHook.state, new: newValue})
        BatchUpdateManager.queueUpdate(instance, newValue)  // Modified in 8th draft
      }
    }
    instance.hooks[instance.hookIndex] = stateHook
  
  instance.hookIndex++
  return [currentHook.state, currentHook.setState]
```

### useEffect Implementation
```
useEffect(effectFn, deps):
  instance = RenderContext.currentInstance
  currentHook = instance.hooks[instance.hookIndex]
  
  if first render:
    log(DEBUG, "Effect initialization")
    effectHook = {
      deps: deps,
      cleanup: null
    }
    instance.hooks[instance.hookIndex] = effectHook
    instance.effects.push(() => {
      log(TRACE, "Effect execution")
      try {  // Added in 8th draft
        if (effectHook.cleanup) {
          log(TRACE, "Effect cleanup")
          runEffectCleanup(effectHook)  // Modified in 8th draft
        }
        effectHook.cleanup = effectFn()
      } catch (error) {
        log(ERROR, "Effect error", {error})
        instance.hasEffectError = true
      }
    })
  else:
    if depsChanged(oldDeps, deps):
      log(DEBUG, "Effect deps changed")
      instance.effects.push(() => {
        try {  // Added in 8th draft
          if (effectHook.cleanup) runEffectCleanup(effectHook)
          effectHook.cleanup = effectFn()
        } catch (error) {
          log(ERROR, "Effect error", {error})
          instance.hasEffectError = true
        }
      })
    currentHook.deps = deps
  
  instance.hookIndex++
```

### Effect Error Handling
```
runEffectCleanup(effect):
  if effect.cleanup:
    try:
      effect.cleanup()
    catch (error):
      log(ERROR, "Effect cleanup error", {error})
      // Continue with other cleanups despite error

runEffect(effect, instance):
  try:
    cleanup = effect.effectFn()
    if cleanup:
      effect.cleanup = cleanup
  catch (error):
    log(ERROR, "Effect execution error", {error})
    // Mark component as errored but don't block other effects
    instance.hasEffectError = true
```

# 6. REFERENCE & CLEANUP MANAGEMENT

### Resource Tracking
```
LiveObjectCounts:
  - componentCount: number
  - domNodeCount: number
  - eventListenerCount: number
  - effectCount: number
  - hookCount: number
  - treeDepth: number
  - activeTimers: number
  - componentBreakdown: Map[ComponentType, number]
  - listenerBreakdown: Map[EventType, number]
```

### Timer Tracking
```
TimerRegistry:
  activeTimers: Map[ComponentInstance, Set<TimerId>]
  
  registerTimer(instance, timerId):
    if !activeTimers.has(instance):
      activeTimers.set(instance, new Set())
    activeTimers.get(instance).add(timerId)
    log(DEBUG, "Registered timer", {instance, timerId})
  
  clearTimer(instance, timerId):
    if activeTimers.has(instance):
      timerSet = activeTimers.get(instance)
      timerSet.delete(timerId)
      if timerSet.size === 0:
        activeTimers.delete(instance)
    log(DEBUG, "Cleared timer", {instance, timerId})
  
  clearAllTimers(instance):
    if activeTimers.has(instance):
      timers = activeTimers.get(instance)
      for timerId in timers:
        clearTimeout(timerId)  // or clearInterval
      activeTimers.delete(instance)
    log(DEBUG, "Cleared all timers", {instance})
```

### Parent/Child Reference Management
```
class ComponentInstance:
  // Existing fields...
  parent: ComponentInstance | null
  children: Set<ComponentInstance>
  
  addChild(child):
    children.add(child)
    child.parent = this
    log(DEBUG, "Added child to component", {parent: this, child})
  
  removeChild(child):
    children.delete(child)
    child.parent = null
    log(DEBUG, "Removed child from component", {parent: this, child})
  
  // Called during component initialization
  establishParentChild(parent, child):
    if child.parent:
      child.parent.removeChild(child)
    parent.addChild(child)
```

### Cleanup Processes
```
cleanupDOMNode(node):
  log(DEBUG, "DOM node cleanup")
  
  // Follow standard cleanup order for DOM nodes
  // 1. Children already handled by component cleanup
  // 2. No effects for DOM nodes
  // 3. Remove event listeners
  removeAllEventListeners(node)
  log(DEBUG, "Removed event listeners")
  
  // 4-5. Clear virtual DOM references
  clearVirtualDOMReferences(node)
  log(DEBUG, "Cleared virtual DOM mappings")
  
  // 6. Clear parent/child refs
  node.parent = null
  node.children = []
  log(DEBUG, "Cleared parent/child references")
  
  // 7. Update counts
  decrementResourceCounts(node)
  
  // 8. Clear any remaining references
  log(DEBUG, "DOM node cleanup complete")

cleanupComponent(instance):
  log(DEBUG, "Component cleanup starting")
  instance.isInCleanup = true  // Added in 8th draft
  
  // Following standard cleanup order:
  
  // 1. Recursively cleanup children first
  for each child in instance.children:
    cleanupComponent(child)
  log(DEBUG, "Child components cleaned up")
  
  // Clear all timers before effect cleanup
  TimerRegistry.clearAllTimers(instance)
  
  // 2. Run effect cleanups in reverse order
  for each effect in reverse(instance.effects):
    if effect.cleanup exists:
      runEffectCleanup(effect)  // Modified in 8th draft
  log(DEBUG, "Effect cleanups complete")
  
  // 3. Remove event listeners
  if instance.domNode:
    removeAllEventListeners(instance.domNode)
  
  // 4. Clear hook data
  instance.hooks = []
  instance.hookIndex = 0
  log(DEBUG, "Hook data cleared")
  
  // 5. Clear DOM references
  instance.domNode = null
  
  // 6. Remove from parent
  if instance.parent:
    instance.parent.removeChild(instance)
  
  // 7. Update resource tracking
  decrementResourceCounts(instance)
  
  // 8. Clear remaining references
  instance.parent = null
  instance.rendered = null
  instance.isInCleanup = false
  
  log(DEBUG, "Component cleanup complete")

cleanupTree(root):
  log(DEBUG, "Tree cleanup starting")
  // Uses post-order traversal to ensure children are cleaned up before parents
  // This naturally follows our cleanup order standard
  for each child in root.children:
    cleanupTree(child)
  cleanupComponent(root)  // Uses standard cleanup order
  log(DEBUG, "Tree cleanup complete")
```

