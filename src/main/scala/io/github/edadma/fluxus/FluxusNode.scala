package io.github.edadma.fluxus

sealed trait FluxusNode

case class ElementNode(
    tag: String,
    attributes: Map[String, String] = Map.empty,
    events: Map[String, () => Unit] = Map.empty,
    children: List[FluxusNode] = List.empty,
) extends FluxusNode

case class TextNode(text: String) extends FluxusNode

case class ComponentNode(
    key: Option[String],
    componentFunction: FluxusComponent,
    props: Props,
) extends FluxusNode
