package io.github.edadma.fluxus

import org.scalajs.dom
import org.scalajs.dom.{Element, Node}

import scala.scalajs.js

sealed trait FluxusNode {
  val key: Option[String]
  val parent: Option[FluxusNode]
  var domNode: Option[Node]
}

case class ElementNode(
    tag: String,
    attrs: Map[String, Any],
    events: Map[String, js.Function1[dom.Event, Unit]],
    children: Vector[FluxusNode],
    parent: Option[FluxusNode],
    var domNode: Option[Node],
    key: Option[String],
    namespace: Option[String] = None,
    ref: Option[Element => Unit] = None,
) extends FluxusNode

case class ComponentNode(
    component: Product => FluxusNode,
    props: Product,
    key: Option[String],
    parent: Option[FluxusNode] = None,
    var domNode: Option[Node] = None,
) extends FluxusNode

case class TextNode(
    text: String,
    parent: Option[FluxusNode],
    var domNode: Option[Node],
) extends FluxusNode:
  val key: Option[String] = None
