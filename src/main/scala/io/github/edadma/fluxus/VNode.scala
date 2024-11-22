package io.github.edadma.fluxus

sealed trait VNode

case class ElementNode(
    tag: String,
    props: Map[String, Any] = Map.empty,
    children: List[VNode] = List.empty,
) extends VNode

case class TextNode(text: String) extends VNode

// Helpers for creating VNodes
def div(children: VNode*): VNode    = ElementNode("div", children = children.toList)
def h1(children: VNode*): VNode     = ElementNode("h1", children = children.toList)
def button(children: VNode*): VNode = ElementNode("button", children = children.toList)

// Event listener helper
case class EventProp(name: String, handler: () => Unit)
def onClick(handler: () => Unit): EventProp = EventProp("onClick", handler)
