package io.github.edadma.fluxus

import org.scalajs.dom

import scala.collection.mutable

sealed trait FluxusNode {
  var domNode: Option[dom.Node] = None
}

case class ElementNode(
    tag: String,
    attributes: Map[String, String] = Map.empty,
    events: Map[String, () => Unit] = Map.empty,
    children: List[FluxusNode] = List.empty,
) extends FluxusNode:
  val eventListenerWrappers: mutable.Map[String, dom.Event => Unit] = mutable.Map.empty

case class TextNode(text: String) extends FluxusNode

case class ComponentNode(
    key: Option[String],
    componentFunction: FluxusComponent,
    props: Props,
    var instance: Option[ComponentInstance] = None,
) extends FluxusNode
