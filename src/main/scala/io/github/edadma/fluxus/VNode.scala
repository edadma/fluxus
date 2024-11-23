package io.github.edadma.fluxus

sealed trait VNode

case class ElementNode(
    tag: String,
    attributes: Map[String, String] = Map.empty,
    events: Map[String, () => Unit] = Map.empty,
    children: List[VNode] = List.empty,
) extends VNode

case class TextNode(text: String) extends VNode

def element(tag: String)(args: (VNode | String | (String, String) | (String, () => Unit))*): VNode = {
  var attributes = Map.empty[String, String]
  var events     = Map.empty[String, () => Unit]
  val children   = List.newBuilder[VNode]

  args.foreach {
    case vnode: VNode                                                 => children += vnode
    case text: String                                                 => children += TextNode(text)
    case (key: String, handler: (() => Unit)) if key.startsWith("on") => events += (key -> handler)
    case attr: (String, String)                                       => attributes += attr
    case other =>
      throw new IllegalStateException(s"Unsupported argument type: $other")
  }

  ElementNode(tag, attributes, events, children.result())
}

// Specialized helpers
def button(args: (VNode | String | (String, String) | (String, () => Unit))*): VNode = element("button")(args*)
def div(args: (VNode | String | (String, String) | (String, () => Unit))*): VNode    = element("div")(args*)
def h1(args: (VNode | String | (String, String) | (String, () => Unit))*): VNode     = element("h1")(args*)
