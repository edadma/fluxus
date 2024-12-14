package io.github.edadma.fluxus

import io.github.edadma.fluxus.core.ComponentInstance
import org.scalajs.dom
import org.scalajs.dom.{Element, Node}

import scala.scalajs.js

sealed trait FluxusNode {
  val parent: Option[FluxusNode]
  var domNode: Option[Node]
  val key: Option[String]
}

case class ElementNode(
    tag: String,
    attrs: Map[String, Any],
    events: Map[String, js.Function1[dom.Event, Unit]],
    children: Vector[FluxusNode],
    parent: Option[FluxusNode],
    var domNode: Option[Node],
    namespace: Option[String] = None,
    ref: Option[Element => Unit] = None,
    key: Option[String] = None,
) extends FluxusNode

case class ComponentNode(
    component: Product => FluxusNode,
    props: Product,
    parent: Option[FluxusNode] = None,
    var domNode: Option[Node] = None,
    key: Option[String] = None,
    var instance: Option[ComponentInstance] = None,
) extends FluxusNode:
  def withKey(k: String): ComponentNode = copy(key = Some(k))

case class TextNode(
    text: String,
    parent: Option[FluxusNode],
    var domNode: Option[Node],
) extends FluxusNode:
  val key: Option[String] = None
