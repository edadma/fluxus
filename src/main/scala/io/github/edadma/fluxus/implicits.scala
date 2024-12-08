//package io.github.edadma.fluxus
//
//import io.github.edadma.fluxus.core.{Component, ComponentNode}
//
//import scala.language.implicitConversions
//
//case class NoProps()
//
//val noProps = NoProps()
//
//implicit class FunctionComponent[P <: Product](f: P => FluxusNode):
//  def <>(props: P): ComponentNode =
//    Component.create(
//      render = f,
//      props = props,
//      key = None,
//      opId = Logger.nextOperationId,
//      name = Some(f.getClass.getSimpleName),
//    )
//
//implicit class ProplessComponent(f: () => FluxusNode):
//  def <>(u: Unit): ComponentNode =
//    Component.create(
//      render = _ => f(), // Ignore the Any parameter and just call f
//      props = noProps,
//      key = None,
//      opId = Logger.nextOperationId,
//      name = Some(f.getClass.getSimpleName),
//    )
