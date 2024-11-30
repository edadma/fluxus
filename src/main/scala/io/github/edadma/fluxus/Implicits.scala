package io.github.edadma.fluxus

import scala.language.implicitConversions

object Implicits:
  implicit class CaseClassComponentOps[P <: Product](val comp: FC[P]) extends AnyVal:
    def <>(props: P): FluxusNode = {
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
    def <>(u: Unit): FluxusNode = {
      FluxusLogger.Props.debug("Implicit NoPropsComponentOps.apply called")
      ComponentNode(
        key = None,
        componentFunction = _ => componentFunction(),
        props = EmptyProps(),
      )
    }
