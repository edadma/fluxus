package io.github.edadma.fluxus.core

import io.github.edadma.fluxus.{ComponentNode, FluxusNode, Hook, logger}

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
  def rerender(): Unit = {
    logger.debug(
      "Re-rendering component instance",
      category = "ComponentInstance",
      Map(
        "instanceId"  -> id,
        "hasRendered" -> rendered.isDefined,
        "hasDomNode"  -> rendered.flatMap(_.domNode).isDefined,
      ),
    )

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
                "oldNode" -> oldNode.toString,
                "newNode" -> newNode.toString,
              ),
            )

            // Use existing reconciliation
            reconcile(rendered, Some(newNode), parent.asInstanceOf[org.scalajs.dom.Element])

            rendered = Some(newNode)

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
  }

  override def toString: String = s"ComponentInstance($id, type=$componentType)"
