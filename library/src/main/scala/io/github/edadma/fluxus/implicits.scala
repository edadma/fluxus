package io.github.edadma.fluxus

import scala.language.implicitConversions

case class NoProps()

val noProps = NoProps()

implicit class FunctionComponent[P <: Product](f: P => FluxusNode):
  def <>(props: P): ComponentNode =
    ComponentNode(
      component = f.asInstanceOf[Any => FluxusNode],
      props = props,
      key = props.productElementNames.zip(props.productIterator)
        .find(_._1 == "key")
        .map(_._2.toString),
      componentHash = System.identityHashCode(f),
    )

implicit class RefFunctionComponent[P <: Product](f: (P, RefHook) => FluxusNode):
  def <>(propsWithRef: (P, RefHook)): ComponentNode =
    ComponentNode(
      component = (props: Any) => {
        val (componentProps, forwardedRef) = props.asInstanceOf[(P, RefHook)]
        f(componentProps, forwardedRef)
      },
      props = propsWithRef,
      key = propsWithRef._1.productElementNames.zip(propsWithRef._1.productIterator)
        .find(_._1 == "key")
        .map(_._2.toString),
      componentHash = System.identityHashCode(f),
    )

implicit class ProplessComponent(f: () => FluxusNode):
  def <>(u: Unit): ComponentNode = noPropsComponentNode(f)

def noPropsComponentNode(f: () => FluxusNode) =
  ComponentNode(
    component = _ => f(), // Ignore the Any parameter and just call f
    props = noProps,
    componentHash = System.identityHashCode(f),
  )

implicit def stringToTextNode(s: String): TextNode         = TextNode(s, None, None)
implicit def nullToEmptyNode(n: Null): FluxusNode          = EmptyNode
implicit def booleanToEmptyNode(b: Boolean): FluxusNode    = EmptyNode
implicit def unitToEmptyNode(u: Unit): FluxusNode          = EmptyNode
implicit def intToTextNode(n: Int): TextNode               = TextNode(n.toString, None, None)
implicit def longToTextNode(n: Long): TextNode             = TextNode(n.toString, None, None)
implicit def doubleToTextNode(n: Double): TextNode         = TextNode(n.toString, None, None)
implicit def floatToTextNode(n: Float): TextNode           = TextNode(n.toString, None, None)
implicit def bigDecimalToTextNode(n: BigDecimal): TextNode = TextNode(n.toString, None, None)
implicit def bigIntToTextNode(n: BigInt): TextNode         = TextNode(n.toString, None, None)
