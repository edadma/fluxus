package io.github.edadma.fluxus

import scala.language.implicitConversions

object Implicits:
  implicit class ComponentOps(val componentFunction: FluxusComponent) extends AnyVal:
    def apply(props: (String, Any)*): FluxusNode = component(componentFunction)(props*)

  implicit def funcToUpdate[T](func: T => T): Update[T] = Update(func)
