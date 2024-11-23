package io.github.edadma.fluxus

object Implicits:
  implicit class ComponentOps(val componentFunction: FluxusComponent) extends AnyVal:
    def apply(props: (String, Any)*): FluxusNode = component(componentFunction)(props*)
