package io.github.edadma.fluxus

import scala.language.implicitConversions

trait Component[P <: Product]:
  def apply(props: P): FluxusNode

object Implicits:
  implicit class CaseClassComponentOps[P <: Product](val comp: Component[P]) extends AnyVal:
    def apply(props: P): FluxusNode = {
      FluxusLogger.Props.debug(
        "Implicit CaseClassComponentOps.apply called",
        Map(
          "componentType" -> comp.getClass.getSimpleName,
          "propsType"     -> props.getClass.getName,
        ),
      )
      component(comp)(props)
    }

  implicit class NoPropsComponentOps(val componentFunction: () => FluxusNode) extends AnyVal:
    def apply(): FluxusNode = {
      FluxusLogger.Props.debug("Implicit NoPropsComponentOps.apply called")
      ComponentNode(
        key = None,
        componentFunction = (_ => componentFunction()),
        props = EmptyProps(),
      )
    }

  implicit def funcToUpdate[T](func: T => T): Update[T] = Update(func)
