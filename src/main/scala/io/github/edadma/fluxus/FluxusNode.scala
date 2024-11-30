package io.github.edadma.fluxus

import org.scalajs.dom

import scala.collection.mutable

sealed trait FluxusNode {
  var domNode: Option[dom.Node] = None
}

class HandlerRef(var handler: dom.Event => Unit)

case class ElementNode(
    tag: String,
    attributes: Map[String, String] = Map.empty,
    events: Map[String, dom.Event => Unit] = Map.empty,
    children: List[FluxusNode] = List.empty,
) extends FluxusNode:
  val eventListenerWrappers: mutable.Map[String, (dom.Event => Unit, HandlerRef)] = mutable.Map.empty

case class TextNode(text: String) extends FluxusNode

case class ComponentNode(
    key: Option[String],
    componentFunction: FluxusComponent,
    props: Product,
    var instance: Option[ComponentInstance] = None,
) extends FluxusNode
