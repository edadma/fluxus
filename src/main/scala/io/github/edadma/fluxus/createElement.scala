//package io.github.edadma.fluxus
//
//import io.github.edadma.fluxus.core.types.*
//
//case class Prop(name: String, value: Any)
//
//implicit class PropOps(name: String) {
//  def :=(value: Any): Prop = Prop(name, value)
//}
//
//// HTML attribute helpers
//def cls: String          = "class"
//def className: String    = "class"
//def id: String           = "id"
//def style: String        = "style"
//def href: String         = "href"
//def src: String          = "src"
//def alt: String          = "alt"
//def title: String        = "title"
//def typ: String          = "type"
//def type_ : String       = "type"
//def value: String        = "value"
//def placeholder: String  = "placeholder"
//def name: String         = "name"
//def disabled: String     = "disabled"
//def checked: String      = "checked"
//def selected: String     = "selected"
//def required: String     = "required"
//def readonly: String     = "readonly"
//def maxLength: String    = "maxLength"
//def minLength: String    = "minLength"
//def pattern: String      = "pattern"
//def autocomplete: String = "autocomplete"
//def role: String         = "role"
//def aria: String         = "aria"
//def tabIndex: String     = "tabIndex"
//def target: String       = "target"
//def rel: String          = "rel"
//def method: String       = "method"
//def action: String       = "action"
//def cols: String         = "cols"
//def rows: String         = "rows"
//def for_ : String        = "for" // underscore since 'for' is reserved
//
//// Event helpers
//def onClick: String       = "onClick"
//def onChange: String      = "onChange"
//def onSubmit: String      = "onSubmit"
//def onFocus: String       = "onFocus"
//def onBlur: String        = "onBlur"
//def onKeyDown: String     = "onKeyDown"
//def onKeyUp: String       = "onKeyUp"
//def onKeyPress: String    = "onKeyPress"
//def onMouseDown: String   = "onMouseDown"
//def onMouseUp: String     = "onMouseUp"
//def onMouseEnter: String  = "onMouseEnter"
//def onMouseLeave: String  = "onMouseLeave"
//def onMouseMove: String   = "onMouseMove"
//def onTouchStart: String  = "onTouchStart"
//def onTouchEnd: String    = "onTouchEnd"
//def onTouchMove: String   = "onTouchMove"
//def onInput: String       = "onInput"
//def onInvalid: String     = "onInvalid"
//def onReset: String       = "onReset"
//def onSelect: String      = "onSelect"
//def onScroll: String      = "onScroll"
//def onWheel: String       = "onWheel"
//def onCopy: String        = "onCopy"
//def onCut: String         = "onCut"
//def onPaste: String       = "onPaste"
//def onContextMenu: String = "onContextMenu"
//def onDoubleClick: String = "onDoubleClick"
//def onDrag: String        = "onDrag"
//def onDragEnd: String     = "onDragEnd"
//def onDragEnter: String   = "onDragEnter"
//def onDragExit: String    = "onDragExit"
//def onDragLeave: String   = "onDragLeave"
//def onDragOver: String    = "onDragOver"
//def onDragStart: String   = "onDragStart"
//def onDrop: String        = "onDrop"
//
//private def processContent(content: Any): Vector[FluxusNode] = content match {
//  case node: FluxusNode => Vector(node)
//  case s: String        => Vector(TextNode(s, None, None, None))
//  case s: Seq[_]        => s.flatMap(processContent).toVector
//  case other            => Vector(TextNode(other.toString, None, None, None))
//}
//
//private def processMixedContent(items: Seq[Any]): (Map[String, Any], Map[String, Any], Vector[FluxusNode]) = {
//  val opId = Logger.nextOperationId
//
//  val props = items.collect {
//    case p: Prop if !p.name.startsWith("on") => p.name -> p.value
//  }.toMap
//
//  val events = items.collect {
//    case p: Prop if p.name.startsWith("on") => p.name -> p.value
//  }.toMap
//
//  Logger.debug(
//    Category.VirtualDOM,
//    "Processing mixed content",
//    opId,
//    Map(
//      "propCount"  -> props.size,
//      "eventCount" -> events.size,
//      "eventNames" -> events.keys.mkString(", "),
//    ),
//  )
//
//  val children = items.filterNot(_.isInstanceOf[Prop]).flatMap(processContent).toVector
//
//  (props, events, children)
//}
//
//def createElement(tag: String, contents: Any*): ElementNode = {
//  val opId = Logger.nextOperationId
//  Logger.debug(Category.VirtualDOM, s"Creating element", opId, Map("tag" -> tag))
//
//  val (props, events, children) = processMixedContent(contents)
//
//  ElementNode(
//    tag = tag,
//    props = props,
//    events = events,
//    children = children,
//    parent = None,
//    domNode = None,
//    key = None,
//    namespace = None,
//    ref = None,
//  )
//}
//
//// Element creation helpers
//def div(contents: Any*): ElementNode      = createElement("div", contents*)
//def button(contents: Any*): ElementNode   = createElement("button", contents*)
//def span(contents: Any*): ElementNode     = createElement("span", contents*)
//def p(contents: Any*): ElementNode        = createElement("p", contents*)
//def h1(contents: Any*): ElementNode       = createElement("h1", contents*)
//def h2(contents: Any*): ElementNode       = createElement("h2", contents*)
//def h3(contents: Any*): ElementNode       = createElement("h3", contents*)
//def h4(contents: Any*): ElementNode       = createElement("h4", contents*)
//def h5(contents: Any*): ElementNode       = createElement("h5", contents*)
//def h6(contents: Any*): ElementNode       = createElement("h6", contents*)
//def input(contents: Any*): ElementNode    = createElement("input", contents*)
//def form(contents: Any*): ElementNode     = createElement("form", contents*)
//def label(contents: Any*): ElementNode    = createElement("label", contents*)
//def select(contents: Any*): ElementNode   = createElement("select", contents*)
//def option(contents: Any*): ElementNode   = createElement("option", contents*)
//def a(contents: Any*): ElementNode        = createElement("a", contents*)
//def article(contents: Any*): ElementNode  = createElement("article", contents*)
//def aside(contents: Any*): ElementNode    = createElement("aside", contents*)
//def br(contents: Any*): ElementNode       = createElement("br", contents*)
//def code(contents: Any*): ElementNode     = createElement("code", contents*)
//def dd(contents: Any*): ElementNode       = createElement("dd", contents*)
//def dl(contents: Any*): ElementNode       = createElement("dl", contents*)
//def dt(contents: Any*): ElementNode       = createElement("dt", contents*)
//def em(contents: Any*): ElementNode       = createElement("em", contents*)
//def fieldset(contents: Any*): ElementNode = createElement("fieldset", contents*)
//def footer(contents: Any*): ElementNode   = createElement("footer", contents*)
//def header(contents: Any*): ElementNode   = createElement("header", contents*)
//def hr(contents: Any*): ElementNode       = createElement("hr", contents*)
//def i(contents: Any*): ElementNode        = createElement("i", contents*)
//def iframe(contents: Any*): ElementNode   = createElement("iframe", contents*)
//def img(contents: Any*): ElementNode      = createElement("img", contents*)
//def legend(contents: Any*): ElementNode   = createElement("legend", contents*)
//def li(contents: Any*): ElementNode       = createElement("li", contents*)
//def main(contents: Any*): ElementNode     = createElement("main", contents*)
//def nav(contents: Any*): ElementNode      = createElement("nav", contents*)
//def ol(contents: Any*): ElementNode       = createElement("ol", contents*)
//def optgroup(contents: Any*): ElementNode = createElement("optgroup", contents*)
//def pre(contents: Any*): ElementNode      = createElement("pre", contents*)
//def progress(contents: Any*): ElementNode = createElement("progress", contents*)
//def section(contents: Any*): ElementNode  = createElement("section", contents*)
//def small(contents: Any*): ElementNode    = createElement("small", contents*)
//def strong(contents: Any*): ElementNode   = createElement("strong", contents*)
//def sub(contents: Any*): ElementNode      = createElement("sub", contents*)
//def sup(contents: Any*): ElementNode      = createElement("sup", contents*)
//def table(contents: Any*): ElementNode    = createElement("table", contents*)
//def tbody(contents: Any*): ElementNode    = createElement("tbody", contents*)
//def td(contents: Any*): ElementNode       = createElement("td", contents*)
//def textarea(contents: Any*): ElementNode = createElement("textarea", contents*)
//def tfoot(contents: Any*): ElementNode    = createElement("tfoot", contents*)
//def th(contents: Any*): ElementNode       = createElement("th", contents*)
//def thead(contents: Any*): ElementNode    = createElement("thead", contents*)
//def tr(contents: Any*): ElementNode       = createElement("tr", contents*)
//def ul(contents: Any*): ElementNode       = createElement("ul", contents*)
