package io.github.edadma.fluxus.api

import io.github.edadma.fluxus.core.types.*
import io.github.edadma.fluxus.logging.Logger

import scala.language.implicitConversions

implicit class FunctionComponent(f: Any => FluxusNode):
  def <>(props: Product): ComponentNode =
    println("functionToComponent")
    Component.create(
      render = f,
      props = props,
      key = None,
      opId = Logger.nextOperationId,
      name = Some(f.getClass.getSimpleName),
    )
