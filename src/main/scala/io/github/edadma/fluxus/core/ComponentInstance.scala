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
  def rerender(): Unit =
    logger.debug(
      "Re-rendering component instance",
      category = "ComponentInstance",
      Map("instanceId" -> id),
    )

    hookIndex = 0 // Reset hook index for new render

    val parent = rendered.get.domNode.get.parentNode

    // Get new tree from component
    val newNode = ComponentInstance.withInstance(this) {
      node.component(node.props)
    }

    logger.debug(
      "Reconciling component",
      category = "ComponentInstance",
      Map(
        "oldNode" -> rendered.toString,
        "newNode" -> newNode.toString,
      ),
    )

    // Use existing reconciliation
    reconcile(rendered, Some(newNode), parent.asInstanceOf[org.scalajs.dom.Element])

    rendered = Some(newNode)

  override def toString: String = s"ComponentInstance($id, type=$componentType)"
