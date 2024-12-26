package io.github.edadma.fluxus

import io.github.edadma.fluxus.core.ComponentInstance

class Context[T](defaultValue: T):
  private var stack: Vector[T] = Vector(defaultValue)

  def pushValue(value: T): Unit = stack = stack :+ value
  def popValue(): Unit          = stack = stack.dropRight(1)
  def currentValue: T           = stack.last

  class Provider:
    val context: Context[T]                     = Context.this
    def apply(props: ProviderProps): FluxusNode = props.children

  val Provider = new Provider()

  case class ProviderProps(value: T, children: FluxusNode)

def createContext[T](defaultValue: T): Context[T] = Context(defaultValue)

def useContext[T](context: Context[T]): T =
  // Verify we're in a component render
  ComponentInstance.current.getOrElse(
    throw new Error("useContext must be called within component render"),
  )

  context.currentValue
