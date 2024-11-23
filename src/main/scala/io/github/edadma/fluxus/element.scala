package io.github.edadma.fluxus

class Attr(name: String):
  def :=(value: String): AttrArg = AttrArg(name, value)

case class AttrArg(name: String, value: String)

class EventAttr(name: String):
  def :=(handler: () => Unit): EventAttrArg = EventAttrArg(name, handler)

case class EventAttrArg(name: String, handler: () => Unit)

type ElementArg = FluxusNode | String | AttrArg | EventAttrArg

def element(tag: String)(args: ElementArg*): FluxusNode = {
  var attributes = Map.empty[String, String]
  var events     = Map.empty[String, () => Unit]
  val children   = List.newBuilder[FluxusNode]

  args.foreach {
    case node: FluxusNode                                 => children += node
    case text: String                                     => children += TextNode(text)
    case EventAttrArg(key: String, handler: (() => Unit)) => events += (key     -> handler)
    case AttrArg(key: String, value: String)              => attributes += (key -> value)
    case null                                             =>
  }

  ElementNode(tag, attributes, events, children.result())
}

val cls         = Attr("class")
val id          = Attr("id")
val href        = Attr("href")
val src         = Attr("src")
val alt         = Attr("alt")
val style       = Attr("style")
val value       = Attr("value")
val typ         = Attr("type")
val placeholder = Attr("placeholder")
val name        = Attr("name")

val onClick   = EventAttr("onClick")
val onChange  = EventAttr("onChange")
val onSubmit  = EventAttr("onSubmit")
val onInput   = EventAttr("onInput")
val onKeyUp   = EventAttr("onKeyUp")
val onKeyDown = EventAttr("onKeyDown")
