package io.github.edadma.fluxus.api

import io.github.edadma.fluxus.core.types._
import io.github.edadma.fluxus.logging.Logger
import io.github.edadma.fluxus.logging.Logger.Category
import org.scalajs.dom

object Element {
  case class Prop(name: String, value: Any)

  implicit class PropOps(name: String) {
    def :=(value: Any): Prop = Prop(name, value)
  }

  // HTML attribute helpers
  def cls: String   = "class"
  def id: String    = "id"
  def style: String = "style"

  // Event helpers
  def onClick: String      = "onClick"
  def onChange: String     = "onChange"
  def onSubmit: String     = "onSubmit"
  def onFocus: String      = "onFocus"
  def onBlur: String       = "onBlur"
  def onKeyDown: String    = "onKeyDown"
  def onKeyUp: String      = "onKeyUp"
  def onKeyPress: String   = "onKeyPress"
  def onMouseDown: String  = "onMouseDown"
  def onMouseUp: String    = "onMouseUp"
  def onMouseEnter: String = "onMouseEnter"
  def onMouseLeave: String = "onMouseLeave"
  def onMouseMove: String  = "onMouseMove"
  def onTouchStart: String = "onTouchStart"
  def onTouchEnd: String   = "onTouchEnd"
  def onTouchMove: String  = "onTouchMove"

  private def processContent(content: Any): Vector[FluxusNode] = content match {
    case node: FluxusNode => Vector(node)
    case s: String        => Vector(TextNode(s, None, None, None))
    case s: Seq[_]        => s.flatMap(processContent).toVector
    case other            => Vector(TextNode(other.toString, None, None, None))
  }

  private def processMixedContent(items: Seq[Any]): (Map[String, Any], Map[String, Any], Vector[FluxusNode]) = {
    val opId = Logger.nextOperationId

    val props = items.collect {
      case p: Prop if !p.name.startsWith("on") => p.name -> p.value
    }.toMap

    val events = items.collect {
      case p: Prop if p.name.startsWith("on") => p.name -> p.value
    }.toMap

    Logger.debug(
      Category.VirtualDOM,
      "Processing mixed content",
      opId,
      Map(
        "propCount"  -> props.size,
        "eventCount" -> events.size,
        "eventNames" -> events.keys.mkString(", "),
      ),
    )

    val children = items.filterNot(_.isInstanceOf[Prop]).flatMap(processContent).toVector

    (props, events, children)
  }

  def createElement(tag: String, contents: Any*): ElementNode = {
    val opId = Logger.nextOperationId
    Logger.debug(Category.VirtualDOM, s"Creating element", opId, Map("tag" -> tag))

    val (props, events, children) = processMixedContent(contents)

    ElementNode(
      tag = tag,
      props = props,
      events = events,
      children = children,
      parent = None,
      domNode = None,
      key = None,
      namespace = None,
      ref = None,
    )
  }

  // Element creation helpers
  def div(contents: Any*): ElementNode    = createElement("div", contents*)
  def button(contents: Any*): ElementNode = createElement("button", contents*)
  def span(contents: Any*): ElementNode   = createElement("span", contents*)
  def p(contents: Any*): ElementNode      = createElement("p", contents*)
  def h1(contents: Any*): ElementNode     = createElement("h1", contents*)
  def h2(contents: Any*): ElementNode     = createElement("h2", contents*)
  def h3(contents: Any*): ElementNode     = createElement("h3", contents*)
  def h4(contents: Any*): ElementNode     = createElement("h4", contents*)
  def h5(contents: Any*): ElementNode     = createElement("h5", contents*)
  def h6(contents: Any*): ElementNode     = createElement("h6", contents*)
  def input(contents: Any*): ElementNode  = createElement("input", contents*)
  def form(contents: Any*): ElementNode   = createElement("form", contents*)
  def label(contents: Any*): ElementNode  = createElement("label", contents*)
  def select(contents: Any*): ElementNode = createElement("select", contents*)
  def option(contents: Any*): ElementNode = createElement("option", contents*)
}
