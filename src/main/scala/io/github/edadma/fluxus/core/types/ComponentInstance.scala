package io.github.edadma.fluxus.core.types

import io.github.edadma.fluxus.core.hooks.Hooks
import io.github.edadma.fluxus.core.util.IdGenerator
import io.github.edadma.fluxus.logging.Logger
import io.github.edadma.fluxus.logging.Logger.Category
import io.github.edadma.fluxus.error.{NodeValidationError, PropValidationError}
import org.scalajs.dom.{Element, Node}

// Represents an instance of a component with fields organized by category
case class ComponentInstance(
    // Core Identity
    id: String = IdGenerator.nextComponentId(),
    component: Any => FluxusNode,
    componentType: String,
    props: Any,
    var stateVersion: Int = 0,

    // Timing and Metrics
    createTime: Long = System.currentTimeMillis(),
    var lastUpdateTime: Long = 0,
    var updateCount: Int = 0,
    var lastRenderDuration: Long = 0,
    var totalRenderTime: Long = 0,

    // Structure
    var parent: Option[ComponentInstance] = None,
    var children: Set[ComponentInstance] = Set.empty,
    var depth: Int = 0,
    var rendered: Option[FluxusNode] = None,
    var renderVersion: Int = 0,

    // State Management
    var isRendering: Boolean = false,
    var needsRender: Boolean = true,
    var isSuspended: Boolean = false,
    var hooks: Vector[Any] = Vector.empty,
    var hookIndex: Int = 0,
    var effects: Vector[() => Unit] = Vector.empty,
    var effectVersion: Int = 0,
    var hasEffectError: Boolean = false,
    var isInCleanup: Boolean = false,
    var preserveState: Boolean = false,

    // Error Handling
    var errorState: Option[Throwable] = None,
    var errorBoundaryStack: Vector[ComponentInstance] = Vector.empty,
    var lastError: Option[Throwable] = None,
    var errorCount: Int = 0,

    // References
    var context: Map[String, Any] = Map.empty,
    var refs: Map[String, Any] = Map.empty,
    var domNode: Option[Node] = None,
    var debugName: Option[String] = None,
) {
  def render(opId: Int): Option[FluxusNode] = {
    Logger.debug(Category.Component, "Starting component render", opId)
    isRendering = true
    hookIndex = 0 // Reset hook index for new render

    try {
      // Set up hooks context
      Hooks.setCurrentInstance(this)

      // Run the component function
      val output = component(props)

      // Store the rendered output
      rendered = Some(output)

      Logger.debug(Category.Component, "Render complete", opId)
      rendered
    } finally {
      isRendering = false
      Hooks.clearCurrentInstance()
    }
  }

  def validate(opId: Int): Unit = {
    // Core Identity validation
    if (id.isEmpty) {
      throw NodeValidationError(
        "Component must have non-empty ID",
        Map("componentType" -> componentType),
        opId,
      )
    }

    // Structure validation
    parent.foreach { p =>
      if (!p.children.contains(this)) {
        throw NodeValidationError(
          "Inconsistent parent-child relationship",
          Map("componentId" -> id, "parentId" -> p.id),
          opId,
        )
      }

      val expectedDepth = p.depth + 1
      if (depth != expectedDepth) {
        throw NodeValidationError(
          "Invalid component depth",
          Map("componentId" -> id, "depth" -> depth, "expectedDepth" -> expectedDepth),
          opId,
        )
      }
    }

    // State validation
    if (isRendering && isInCleanup) {
      throw NodeValidationError(
        "Component cannot be rendering during cleanup",
        Map("componentId" -> id),
        opId,
      )
    }

    // Error count validation
    val actualErrorCount = errorBoundaryStack.size + lastError.map(_ => 1).getOrElse(0)
    if (errorCount != actualErrorCount) {
      throw NodeValidationError(
        "Error count mismatch",
        Map("componentId" -> id, "errorCount" -> errorCount, "actualErrorCount" -> actualErrorCount),
        opId,
      )
    }

    // Hook validation
    if (hookIndex > hooks.size) {
      throw NodeValidationError(
        "Hook index out of bounds",
        Map("componentId" -> id, "hookIndex" -> hookIndex, "hooksSize" -> hooks.size),
        opId,
      )
    }
  }

  def addChild(child: ComponentInstance, opId: Int): Unit = {
    Logger.debug(Category.Component, s"Adding child to component", opId, Map("parentId" -> id, "childId" -> child.id))

    // Update relationships
    children += child
    child.parent = Some(this)
    child.depth = depth + 1

    // Validate new state
    child.validate(opId)
    validate(opId)
  }

  def removeChild(child: ComponentInstance, opId: Int): Unit = {
    Logger.debug(
      Category.Component,
      s"Removing child from component",
      opId,
      Map("parentId" -> id, "childId" -> child.id),
    )

    // Update relationships
    children -= child
    child.parent = None

    // Validate new state
    child.validate(opId)
    validate(opId)
  }

  def initialize(opId: Int): Unit = {
    Logger.debug(
      Category.Component,
      s"Initializing component",
      opId,
      Map("componentId" -> id, "componentType" -> componentType),
    )

    // Reset all version counters
    stateVersion = 0
    renderVersion = 0
    effectVersion = 0

    // Reset state flags
    needsRender = true
    isRendering = false
    isInCleanup = false
    isSuspended = false
    preserveState = false

    // Reset hooks
    hookIndex = 0
    hooks = Vector.empty
    effects = Vector.empty
    hasEffectError = false

    // Reset error state
    errorState = None
    errorBoundaryStack = Vector.empty
    lastError = None
    errorCount = 0

    // Validate initial state
    validate(opId)
  }

  def markNeedsRender(opId: Int): Unit = {
    if (!needsRender) {
      Logger.debug(Category.Component, s"Marking component for render", opId, Map("componentId" -> id))
      needsRender = true
    }
  }

  def incrementStateVersion(opId: Int): Unit = {
    stateVersion += 1
    Logger.trace(
      Category.StateEffect,
      s"Incremented state version",
      opId,
      Map("componentId" -> id, "newVersion" -> stateVersion),
    )
  }

  def recordError(error: Throwable, opId: Int): Unit = {
    errorState = Some(error)
    lastError = Some(error)
    errorCount += 1
    Logger.error(
      Category.Component,
      s"Component error recorded",
      opId,
      Map("componentId" -> id, "error" -> error.getMessage),
    )
  }
}

// Factory methods for creating components
object Component {
  def create[P <: Product](
      render: P => FluxusNode,
      props: P,
      key: Option[String] = None,
      opId: Int,
      name: Option[String] = None,
  ): ComponentNode = {
    Logger.debug(Category.Component, "Creating component", opId)

    validateProps(props, opId)

    val instance = ComponentInstance(
      component = render.asInstanceOf[Any => FluxusNode],
      componentType = name.getOrElse(render.getClass.getSimpleName),
      props = props, // No more conversion to Map needed
      debugName = name,
    )

    instance.initialize(opId)

    ComponentNode(
      component = render,
      props = props, // Store the case class directly
      instance = Some(instance),
      key = key,
    )
  }

  private def validateProps(props: Any, opId: Int): Unit = {
    // Only need to verify it's a case class
    if (!props.isInstanceOf[Product]) {
      throw PropValidationError(
        "Props must be a case class",
        Map("propsType" -> props.getClass.getName),
        opId,
      )
    }
  }
}
