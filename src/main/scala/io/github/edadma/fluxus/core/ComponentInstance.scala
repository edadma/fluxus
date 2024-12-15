package io.github.edadma.fluxus.core

import io.github.edadma.fluxus.{FluxusNode, Hook}

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
)
