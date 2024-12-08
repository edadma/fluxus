package io.github.edadma.fluxus.core

import org.scalajs.dom.{Element, Node}

sealed trait FluxusNode {
  val key: Option[String]
  val parent: Option[FluxusNode]
  var domNode: Option[Node]
}

case class ElementNode(
    tag: String,
    props: Map[String, Any],
    events: Map[String, Any],
    children: Vector[FluxusNode],
    parent: Option[FluxusNode],
    var domNode: Option[Node],
    key: Option[String],
    namespace: Option[String] = None,
    ref: Option[Element => Unit] = None,
) extends FluxusNode

case class ComponentNode(
    component: ? => FluxusNode,
    props: Any,
    instance: Option[ComponentInstance],
    key: Option[String],
) extends FluxusNode {
  val parent: Option[FluxusNode] = None
  var domNode: Option[Node]      = None
}

case class TextNode(
    text: String,
    parent: Option[FluxusNode],
    var domNode: Option[Node],
    key: Option[String],
) extends FluxusNode
