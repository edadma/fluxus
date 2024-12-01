package io.github.edadma.fluxus.core.types

import org.scalajs.dom.{Element, Node, Text}

sealed trait FluxusNode {
  val key: Option[String]
  val parent: Option[FluxusNode]
  val domNode: Option[Node]
}

case class ElementNode(
    tag: String,
    props: Map[String, Any],
    events: Map[String, Any],
    children: Vector[FluxusNode],
    parent: Option[FluxusNode],
    domNode: Option[Element],
    key: Option[String],
    namespace: Option[String] = None,
    ref: Option[Element => Unit] = None,
) extends FluxusNode

case class ComponentNode(
    component: Any => FluxusNode,
    props: Map[String, Any],
    instance: Option[ComponentInstance],
    key: Option[String],
) extends FluxusNode {
  val parent: Option[FluxusNode] = None
  val domNode: Option[Node]      = None
}

case class TextNode(
    text: String,
    parent: Option[FluxusNode],
    domNode: Option[Text],
    key: Option[String],
) extends FluxusNode
