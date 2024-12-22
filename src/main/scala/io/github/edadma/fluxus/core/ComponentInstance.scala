package io.github.edadma.fluxus.core

import io.github.edadma.fluxus.{ComponentNode, EffectHook, FluxusNode, Hook, logger}

object ComponentInstance:
  private var currentInstance: Option[ComponentInstance] = None
  private var instanceCounter: Long                      = 0

  def current: Option[ComponentInstance] = currentInstance

  def withInstance[T](instance: ComponentInstance)(f: => T): T =
    val prev = currentInstance
    currentInstance = Some(instance)
    try f
    finally currentInstance = prev

  def nextId: String =
    instanceCounter += 1
    s"comp-$instanceCounter"

case class ComponentInstance(
    id: String = ComponentInstance.nextId,
    componentType: String, // For debugging/logging
    var hooks: Vector[Hook] = Vector.empty,
    var hookIndex: Int = 0,
    var parent: Option[ComponentInstance] = None,
    var rendered: Option[FluxusNode] = None,
    var node: ComponentNode,
):
  def createInitialRender(): Unit = {
    hookIndex = 0 // Reset hook index for new render

    rendered match {
      case None =>
        // Get new tree from component
        val newNode = ComponentInstance.withInstance(this) {
          node.component(node.props)
        }

        rendered = Some(newNode)

        // Run initial effects after the render is complete
        BatchScheduler.handleEffects(Set(this))

      case Some(_) =>
        logger.error(
          "Attempted to do initial render on already rendered component",
          category = "ComponentInstance",
          Map("instanceId" -> id),
        )
    }
  }

  def cleanup(): Unit = {
    logger.debug(
      "Cleaning up component instance",
      category = "ComponentInstance",
      Map("id" -> id),
    )

    // Run cleanup functions for all effect hooks
    hooks.reverse.foreach {
      case hook: EffectHook =>
        hook.cleanup.foreach { cleanup =>
          logger.debug(
            "Running effect cleanup",
            category = "ComponentInstance",
            Map("hasCleanup" -> hook.cleanup.isDefined.toString),
          )
          cleanup()
        }
      case _ => // Not an effect hook
    }
  }

  def rerender(): Unit = {
    logger.debug(
      "Before re-rendering component instance",
      category = "ComponentInstance",
      Map(
        "hooks"       -> hooks.map(_.toString).mkString(", "),
        "instanceId"  -> id,
        "hasRendered" -> rendered.isDefined,
        "hasDomNode"  -> rendered.flatMap(_.domNode).isDefined,
      ),
    )

    val previousHookCount = hooks.length

    hookIndex = 0 // Reset hook index for new render

    rendered match {
      case Some(oldNode) =>
        oldNode.domNode match {
          case Some(domNode) =>
            val parent = domNode.parentNode

            // Get new tree from component
            val newNode = ComponentInstance.withInstance(this) {
              node.component(node.props)
            }

            logger.debug(
              "Reconciling component",
              category = "ComponentInstance",
              Map(
                "oldNode"           -> oldNode.toString,
                "newNode"           -> newNode.toString,
                "hasParent"         -> (parent != null).toString,
                "previousHookCount" -> previousHookCount.toString,
                "currentHookIndex"  -> hookIndex.toString,
              ),
            )

            // Validate that we used the same number of hooks
            if (hookIndex != previousHookCount) {
              throw new Error(
                s"Invalid number of hooks. Previous render had $previousHookCount hooks " +
                  s"but current render has $hookIndex hooks. " +
                  "Hooks must be called in the exact same order on every render.",
              )
            }

            // Use existing reconciliation
            reconcile(rendered, Some(newNode), parent.asInstanceOf[org.scalajs.dom.Element])

            rendered = Some(newNode)
            BatchScheduler.handleEffects(Set(this))
          case None =>
            logger.error(
              "Component instance has rendered node but no DOM node",
              category = "ComponentInstance",
              Map("instanceId" -> id),
            )
        }

      case None =>
        logger.error(
          "Attempted to rerender component with no initial render",
          category = "ComponentInstance",
          Map("instanceId" -> id),
        )
    }

    logger.debug(
      "After render",
      category = "ComponentInstance",
      Map(
        "hooks" -> hooks.map(_.toString).mkString(", "),
      ),
    )
  }

  override def toString: String = s"ComponentInstance($id, type=$componentType)"
