package io.github.edadma.fluxus

import org.scalajs.dom

sealed trait FluxusNode {
  var domNode: Option[dom.Node] = None
}

case class ElementNode(
    tag: String,
    attributes: Map[String, String] = Map.empty,
    events: Map[String, () => Unit] = Map.empty,
    children: List[FluxusNode] = List.empty,
) extends FluxusNode:
  var eventListenerWrappers: Map[String, dom.Event => Unit] = Map.empty

case class TextNode(text: String) extends FluxusNode

case class ComponentNode(
    key: Option[String],
    componentFunction: FluxusComponent,
    props: Props,
    var instance: Option[ComponentInstance] = None,
) extends FluxusNode
