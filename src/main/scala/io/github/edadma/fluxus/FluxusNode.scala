package io.github.edadma.fluxus

sealed trait FluxusNode

case class ElementNode(
    tag: String,
    attributes: Map[String, String] = Map.empty,
    events: Map[String, () => Unit] = Map.empty,
    children: List[FluxusNode] = List.empty,
) extends FluxusNode

case class TextNode(text: String) extends FluxusNode

def element(tag: String)(args: (FluxusNode | String | (String, String) | (String, () => Unit))*): FluxusNode = {
  var attributes = Map.empty[String, String]
  var events     = Map.empty[String, () => Unit]
  val children   = List.newBuilder[FluxusNode]

  args.foreach {
    case vnode: FluxusNode                                            => children += vnode
    case text: String                                                 => children += TextNode(text)
    case (key: String, handler: (() => Unit)) if key.startsWith("on") => events += (key -> handler)
    case attr: (String, String)                                       => attributes += attr
    case other =>
      throw new IllegalStateException(s"Unsupported argument type: $other")
  }

  ElementNode(tag, attributes, events, children.result())
}

// Specialized helpers
def button(args: (FluxusNode | String | (String, String) | (String, () => Unit))*): FluxusNode =
  element("button")(args*)
def div(args: (FluxusNode | String | (String, String) | (String, () => Unit))*): FluxusNode  = element("div")(args*)
def span(args: (FluxusNode | String | (String, String) | (String, () => Unit))*): FluxusNode = element("span")(args*)
def h1(args: (FluxusNode | String | (String, String) | (String, () => Unit))*): FluxusNode   = element("h1")(args*)
def p(args: (FluxusNode | String | (String, String) | (String, () => Unit))*): FluxusNode    = element("p")(args*)
def ul(args: (FluxusNode | String | (String, String) | (String, () => Unit))*): FluxusNode   = element("ul")(args*)
def li(args: (FluxusNode | String | (String, String) | (String, () => Unit))*): FluxusNode   = element("li")(args*)

// Represents a component node that needs to be rendered
case class ComponentNode(
    key: Option[String],
    componentFunction: Props => FluxusNode, // The component function
    props: Props,                           // The props to pass to the component
) extends FluxusNode
