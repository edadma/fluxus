```
setState: (newValue) => {
  if instance.isInCleanup:  // Added in 8th draft
    log(WARN, "State update during cleanup - deferring to next render")
    BatchUpdateManager.queueUpdate(instance.parent, newValue)
    return
        
  log(DEBUG, "State update", {old: stateHook.state, new: newValue})
  BatchUpdateManager.queueUpdate(instance, newValue)
}
```

# 8. RENDER SCHEDULING

### Component Render Process
1. Set isRendering flag
2. Reset hook state
3. Clear pending effects
4. Log render start (DEBUG)
5. Perform render
6. Clear isRendering
7. Log render complete (DEBUG)
8. Run effects
9. Update resource counts

### Batch Update Management
```
BatchUpdateManager:
  pendingUpdates: Map<ComponentInstance, Set<StateUpdate>>
  isProcessingBatch: boolean
  
  queueUpdate(instance, stateUpdate):
    if !pendingUpdates.has(instance):
      pendingUpdates.set(instance, new Set())
    pendingUpdates.get(instance).add(stateUpdate)
    
    if !isProcessingBatch:
      scheduleBatchProcess()
  
  processBatch():
    isProcessingBatch = true
    
    // Get all unique components with updates
    components = new Set(pendingUpdates.keys())
    
    // Process each component's updates
    for component in components:
      updates = pendingUpdates.get(component)
      pendingUpdates.delete(component)
      
      // Apply all updates
      for update in updates:
        applyUpdate(component, update)
      
      // Single render for all updates
      renderComponent(component)
    
    isProcessingBatch = false
    
    // Check for updates that occurred during processing
    if pendingUpdates.size > 0:
      scheduleBatchProcess()
```

### State Updates
- Always outside render phase
- Schedule instead of immediate
- Log state changes (DEBUG)
- Trigger re-renders
- Update resource metrics

### Cleanup-time State Updates
```
// Modify state hook to detect cleanup phase
setState(newValue):
  if instance.isInCleanup:
    log(WARN, "State update during cleanup - deferring to next render")
    BatchUpdateManager.queueUpdate(instance.parent, newValue)
    return
    
  BatchUpdateManager.queueUpdate(instance, newValue)
```

### Update Scheduling Rules
1. Updates are batched by default
2. Each component gets one render per batch
3. Child updates trigger parent renders
4. Cleanup updates defer to next batch
5. Timer updates queue normally
6. Effect updates run after render complete
