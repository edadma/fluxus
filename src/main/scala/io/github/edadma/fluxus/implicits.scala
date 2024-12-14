package io.github.edadma.fluxus

case class NoProps()

val noProps = NoProps()

implicit class FunctionComponent[P <: Product](f: P => FluxusNode):
  def <>(props: P): ComponentNode =
    ComponentNode(
      component = f.asInstanceOf[Any => FluxusNode],
      props = props,
    )

implicit class ProplessComponent(f: () => FluxusNode):
  def <>(u: Unit): ComponentNode =
    ComponentNode(
      component = _ => f(), // Ignore the Any parameter and just call f
      props = noProps,
    )
