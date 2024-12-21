package io.github.edadma.fluxus

import org.scalajs.dom

import scala.scalajs.js

case class Attribute(name: String, value: Any)

implicit class AttributeOps(name: String):
  def :=(value: Any): Attribute = Attribute(name, value)

// HTML attribute helpers
def cls: String            = "class"
def className: String      = "class"
def id: String             = "id"
def style: String          = "style"
def href: String           = "href"
def src: String            = "src"
def alt: String            = "alt"
def title: String          = "title"
def typ: String            = "type"
def type_ : String         = "type"
def defaultValue: String   = "value"
def value: String          = "value"
def value_ : String        = "value"
def placeholder: String    = "placeholder"
def name: String           = "name"
def disabled: String       = "disabled"
def defaultChecked: String = "checked"
def checked: String        = "checked"
def selected: String       = "selected"
def required: String       = "required"
def readonly: String       = "readonly"
def maxLength: String      = "maxLength"
def minLength: String      = "minLength"
def pattern: String        = "pattern"
def autocomplete: String   = "autocomplete"
def role: String           = "role"
def aria: String           = "aria"
def tabIndex: String       = "tabIndex"
def target: String         = "target"
def rel: String            = "rel"
def method: String         = "method"
def action: String         = "action"
def cols: String           = "cols"
def rows: String           = "rows"
def htmlFor: String        = "for"

def key: String   = "key"
def key_ : String = "key"

// Event helpers
def onClick: String       = "onClick"
def onDblClick: String    = "onDblClick"
def onChange: String      = "onChange"
def onSubmit: String      = "onSubmit"
def onFocus: String       = "onFocus"
def onBlur: String        = "onBlur"
def onKeyDown: String     = "onKeyDown"
def onKeyUp: String       = "onKeyUp"
def onKeyPress: String    = "onKeyPress"
def onMouseDown: String   = "onMouseDown"
def onMouseUp: String     = "onMouseUp"
def onMouseEnter: String  = "onMouseEnter"
def onMouseLeave: String  = "onMouseLeave"
def onMouseMove: String   = "onMouseMove"
def onTouchStart: String  = "onTouchStart"
def onTouchEnd: String    = "onTouchEnd"
def onTouchMove: String   = "onTouchMove"
def onInput: String       = "onInput"
def onInvalid: String     = "onInvalid"
def onReset: String       = "onReset"
def onSelect: String      = "onSelect"
def onScroll: String      = "onScroll"
def onWheel: String       = "onWheel"
def onCopy: String        = "onCopy"
def onCut: String         = "onCut"
def onPaste: String       = "onPaste"
def onContextMenu: String = "onContextMenu"
def onDoubleClick: String = "onDoubleClick"
def onDrag: String        = "onDrag"
def onDragEnd: String     = "onDragEnd"
def onDragEnter: String   = "onDragEnter"
def onDragExit: String    = "onDragExit"
def onDragLeave: String   = "onDragLeave"
def onDragOver: String    = "onDragOver"
def onDragStart: String   = "onDragStart"
def onDrop: String        = "onDrop"

private def processContent(content: Any): Vector[FluxusNode] = content match {
  case node: FluxusNode                     => Vector(node)
  case s: Seq[_]                            => s.flatMap(processContent).toVector
  case "" | null | true | false | () | None => Vector.empty
  case Some(c)                              => processContent(c)
  case other                                => Vector(TextNode(other.toString, None, None))
}

private def processMixedContent(items: Seq[Any])
    : (Map[String, Any], Map[String, js.Function1[dom.Event, Unit]], Vector[FluxusNode]) = {
  val attrs = items.collect {
    case Attribute(name, value) if !name.startsWith("on") => name -> value
  }.toMap

  val events = items.collect {
    case Attribute(name, f) if name.startsWith("on") =>
      val wrapper: js.Function1[dom.Event, Unit] = f match {
        case f: (() => _) => (_: dom.Event) => f()
        case f            => f.asInstanceOf[dom.Event => Unit]
      }

      name -> wrapper
  }.toMap

  val children = items.filterNot(_.isInstanceOf[Attribute]).flatMap(processContent).toVector

  (attrs, events, children)
}

def createElement(tag: String, contents: Any*): ElementNode = {
  val (attrs, events, children) = processMixedContent(contents)

  // Extract key from attrs and remove it
  val key             = attrs.get("key").map(_.toString)
  val attrsWithoutKey = attrs - "key"

  ElementNode(
    tag = tag,
    attrs = attrs,
    events = events,
    children = children,
    parent = None,
    domNode = None,
    namespace = None,
    ref = None,
  )
}

// Element creation helpers
def div(contents: Any*): ElementNode      = createElement("div", contents*)
def button(contents: Any*): ElementNode   = createElement("button", contents*)
def span(contents: Any*): ElementNode     = createElement("span", contents*)
def p(contents: Any*): ElementNode        = createElement("p", contents*)
def h1(contents: Any*): ElementNode       = createElement("h1", contents*)
def h2(contents: Any*): ElementNode       = createElement("h2", contents*)
def h3(contents: Any*): ElementNode       = createElement("h3", contents*)
def h4(contents: Any*): ElementNode       = createElement("h4", contents*)
def h5(contents: Any*): ElementNode       = createElement("h5", contents*)
def h6(contents: Any*): ElementNode       = createElement("h6", contents*)
def input(contents: Any*): ElementNode    = createElement("input", contents*)
def form(contents: Any*): ElementNode     = createElement("form", contents*)
def label(contents: Any*): ElementNode    = createElement("label", contents*)
def select(contents: Any*): ElementNode   = createElement("select", contents*)
def option(contents: Any*): ElementNode   = createElement("option", contents*)
def a(contents: Any*): ElementNode        = createElement("a", contents*)
def article(contents: Any*): ElementNode  = createElement("article", contents*)
def aside(contents: Any*): ElementNode    = createElement("aside", contents*)
def br(contents: Any*): ElementNode       = createElement("br", contents*)
def code(contents: Any*): ElementNode     = createElement("code", contents*)
def dd(contents: Any*): ElementNode       = createElement("dd", contents*)
def dl(contents: Any*): ElementNode       = createElement("dl", contents*)
def dt(contents: Any*): ElementNode       = createElement("dt", contents*)
def em(contents: Any*): ElementNode       = createElement("em", contents*)
def fieldset(contents: Any*): ElementNode = createElement("fieldset", contents*)
def footer(contents: Any*): ElementNode   = createElement("footer", contents*)
def header(contents: Any*): ElementNode   = createElement("header", contents*)
def hr(contents: Any*): ElementNode       = createElement("hr", contents*)
def i(contents: Any*): ElementNode        = createElement("i", contents*)
def iframe(contents: Any*): ElementNode   = createElement("iframe", contents*)
def img(contents: Any*): ElementNode      = createElement("img", contents*)
def legend(contents: Any*): ElementNode   = createElement("legend", contents*)
def li(contents: Any*): ElementNode       = createElement("li", contents*)
def main(contents: Any*): ElementNode     = createElement("main", contents*)
def nav(contents: Any*): ElementNode      = createElement("nav", contents*)
def ol(contents: Any*): ElementNode       = createElement("ol", contents*)
def optgroup(contents: Any*): ElementNode = createElement("optgroup", contents*)
def pre(contents: Any*): ElementNode      = createElement("pre", contents*)
def progress(contents: Any*): ElementNode = createElement("progress", contents*)
def section(contents: Any*): ElementNode  = createElement("section", contents*)
def small(contents: Any*): ElementNode    = createElement("small", contents*)
def strong(contents: Any*): ElementNode   = createElement("strong", contents*)
def sub(contents: Any*): ElementNode      = createElement("sub", contents*)
def sup(contents: Any*): ElementNode      = createElement("sup", contents*)
def table(contents: Any*): ElementNode    = createElement("table", contents*)
def tbody(contents: Any*): ElementNode    = createElement("tbody", contents*)
def td(contents: Any*): ElementNode       = createElement("td", contents*)
def textarea(contents: Any*): ElementNode = createElement("textarea", contents*)
def tfoot(contents: Any*): ElementNode    = createElement("tfoot", contents*)
def th(contents: Any*): ElementNode       = createElement("th", contents*)
def thead(contents: Any*): ElementNode    = createElement("thead", contents*)
def tr(contents: Any*): ElementNode       = createElement("tr", contents*)
def ul(contents: Any*): ElementNode       = createElement("ul", contents*)
def figure(contents: Any*): ElementNode   = createElement("figure", contents*)
