package io.github.edadma.fluxus

extension (sym: Symbol)
  def :=(value: Any): (String, Any) = (sym.name, value)

type ElementArg = FluxusNode | String | (String, Any)

def element(tag: String)(args: ElementArg*): FluxusNode = {
  var attributes = Map.empty[String, String]
  var events     = Map.empty[String, () => Unit]
  val children   = List.newBuilder[FluxusNode]

  args foreach {
    case node: FluxusNode                                             => children += node
    case text: String                                                 => children += TextNode(text)
    case (key: String, handler: (() => Unit)) if key.startsWith("on") => events += (key     -> handler)
    case (key: String, value: String)                                 => attributes += (key -> value)
    case null                                                         =>
    case (_, _)                                                       => ???
  }

  ElementNode(tag, attributes, events, children.result())
}

val cls         = Symbol("class")
val id          = Symbol("id")
val href        = Symbol("href")
val src         = Symbol("src")
val alt         = Symbol("alt")
val style       = Symbol("style")
val value       = Symbol("value")
val typ         = Symbol("type")
val placeholder = Symbol("placeholder")
val name        = Symbol("name")

val onClick   = Symbol("onClick")
val onChange  = Symbol("onChange")
val onSubmit  = Symbol("onSubmit")
val onInput   = Symbol("onInput")
val onKeyUp   = Symbol("onKeyUp")
val onKeyDown = Symbol("onKeyDown")
