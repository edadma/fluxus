package io.github.edadma.fluxus

import org.scalajs.dom.Event

extension (sym: Symbol)
  def :=(value: Any): (String, Any) = (sym.name, value)

type ElementArg = FluxusNode | String | (String, Any) | Seq[FluxusNode | String | (String, Any)]

def element(tag: String)(args: ElementArg*): FluxusNode = {
  var attributes = Map.empty[String, String]
  var events     = Map.empty[String, Event => Unit]
  val children   = List.newBuilder[FluxusNode]

  def elementArgs(s: Seq[ElementArg]): Unit =
    s foreach {
      case node: FluxusNode     => children += node
      case seq: Seq[ElementArg] => elementArgs(seq)
      case text: String         => children += TextNode(text)
      case (key: String, handler: (() => Unit) @unchecked) if key.startsWith("on") =>
        events += (key -> ((_: Event) => handler()))
      case (key: String, handler: (Event => Unit) @unchecked) if key.startsWith("on") => events += (key     -> handler)
      case (key: String, value: String)                                               => attributes += (key -> value)
      case (key: String, value: Boolean) => attributes += (key -> value.toString)
      case null                          =>
      case (k, v)                        => sys.error(s"unknown element arg: '$k', '$v'")
    }

  elementArgs(args)

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
val checked     = Symbol("checked")
val placeholder = Symbol("placeholder")
val name        = Symbol("name")

val onClick      = Symbol("onClick")
val onChange     = Symbol("onChange")
val onSubmit     = Symbol("onSubmit")
val onInput      = Symbol("onInput")
val onKeyUp      = Symbol("onKeyUp")
val onKeyDown    = Symbol("onKeyDown")
val onMouseEnter = Symbol("onMouseEnter")
val onMouseLeave = Symbol("onMouseLeave")
