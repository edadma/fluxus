package io.github.edadma.fluxus.api

import io.github.edadma.fluxus.core.types._
import io.github.edadma.fluxus.logging.Logger
import io.github.edadma.fluxus.logging.Logger.Category

object Element {
  case class Prop(name: String, value: Any)

  implicit class PropOps(name: String) {
    def :=(value: Any): Prop = Prop(name, value)
  }

  def cls: String   = "class"
  def id: String    = "id"
  def style: String = "style"

  private def processContent(content: Any): Vector[FluxusNode] = content match {
    case node: FluxusNode => Vector(node)
    case s: String        => Vector(TextNode(s, None, None, None))
    case s: Seq[_]        => s.flatMap(processContent).toVector
    case other            => Vector(TextNode(other.toString, None, None, None))
  }

  private def processMixedContent(items: Seq[Any]): (Map[String, Any], Vector[FluxusNode]) = {
    val props    = items.collect { case p: Prop => p.name -> p.value }.toMap
    val children = items.filterNot(_.isInstanceOf[Prop]).flatMap(processContent).toVector
    (props, children)
  }

  def createElement(tag: String, contents: Any*): ElementNode = {
    val opId = Logger.nextOperationId
    Logger.debug(Category.VirtualDOM, s"Creating element", opId, Map("tag" -> tag))

    val (props, children) = processMixedContent(contents)

    ElementNode(
      tag = tag,
      props = props,
      events = Map.empty,
      children = children,
      parent = None,
      domNode = None,
      key = None,
    )
  }

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
}
