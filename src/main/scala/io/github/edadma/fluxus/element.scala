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
val tabindex    = Symbol("tabindex")
val disabled    = Symbol("disabled")
val readonly    = Symbol("readonly")
val required    = Symbol("required")
val selected    = Symbol("selected")
val title       = Symbol("title")
val target      = Symbol("target")
val rel         = Symbol("rel")
val role        = Symbol("role")
val colspan     = Symbol("colspan")
val rowspan     = Symbol("rowspan")
val maxlength   = Symbol("maxlength")
val minlength   = Symbol("minlength")
val pattern     = Symbol("pattern")
val autofocus   = Symbol("autofocus")
val height      = Symbol("height")
val width       = Symbol("width")
val min         = Symbol("min")
val max         = Symbol("max")
val step        = Symbol("step")
val for_        = Symbol("for") // Note: underscore because 'for' is a keyword
val action      = Symbol("action")
val method      = Symbol("method")
val rows        = Symbol("rows")
val cols        = Symbol("cols")
val defer       = Symbol("defer")
val async       = Symbol("async")
val spellcheck  = Symbol("spellcheck")
val draggable   = Symbol("draggable")
val ariaLabel   = Symbol("aria-label")
val ariaHidden  = Symbol("aria-hidden")

val onClick      = Symbol("onClick")
val onChange     = Symbol("onChange")
val onSubmit     = Symbol("onSubmit")
val onInput      = Symbol("onInput")
val onKeyUp      = Symbol("onKeyUp")
val onKeyDown    = Symbol("onKeyDown")
val onMouseEnter = Symbol("onMouseEnter")
val onMouseLeave = Symbol("onMouseLeave")
