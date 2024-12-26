package io.github.edadma.fluxus

import io.github.edadma.fluxus.core.ComponentInstance
import org.scalajs.dom
import org.scalajs.dom.{Element, Node}

import scala.scalajs.js

sealed trait FluxusNode {
  val parent: Option[FluxusNode]
  var domNode: Option[Node]
  val key: Option[String]

  override def toString: String = this match {
    case ElementNode(tag, attrs, _, children, _, _, _, _, _) =>
      s"ElementNode($tag, attrs=$attrs, children=${children.length})"
    case TextNode(text, _, _) =>
      s"TextNode($text)"
    case ComponentNode(component, props, _, _, key, _) =>
      s"ComponentNode(${props.getClass.getSimpleName}, key=$key)"
    case RawNode(element, _, _, key) =>
      s"RawNode(${element.tagName}, key=$key)"
  }
}

case class RawNode(
    element: dom.Element,
    parent: Option[FluxusNode] = None,
    var domNode: Option[Node] = None,
    key: Option[String] = None,
) extends FluxusNode

case class ElementNode(
    tag: String,
    attrs: Map[String, Any],
    var events: Map[String, js.Function1[dom.Event, Unit]],
    children: Vector[FluxusNode],
    parent: Option[FluxusNode],
    var domNode: Option[Node],
    namespace: Option[String] = None,
    ref: Option[Element => Unit] = None,
    key: Option[String] = None,
) extends FluxusNode {
  // Override hashCode to exclude parent
  override def hashCode: Int = {
    val prime  = 31
    var result = 1
    result = prime * result + tag.hashCode
    result = prime * result + attrs.hashCode
    result = prime * result + children.hashCode
    result = prime * result + namespace.hashCode
    result = prime * result + key.hashCode
    result
  }

  // Override equals to exclude parent
  override def equals(other: Any): Boolean = other match {
    case that: ElementNode =>
      tag == that.tag &&
      attrs == that.attrs &&
      events == that.events &&
      children == that.children &&
      namespace == that.namespace &&
      ref == that.ref &&
      key == that.key
    case _ => false
  }
}

case class ComponentNode(
    component: Product => FluxusNode,
    props: Product,
    parent: Option[FluxusNode] = None,
    var domNode: Option[Node] = None,
    key: Option[String] = None,
    var instance: Option[ComponentInstance] = None,
) extends FluxusNode {
  override def hashCode: Int = {
    val prime  = 31
    var result = 1
    result = prime * result + component.hashCode // Include component function
    result = prime * result + props.hashCode
    result = prime * result + key.hashCode
    // Note: component function is not included in hashCode as functions don't have reliable hash codes
    result
  }

  override def equals(other: Any): Boolean = other match {
    case that: ComponentNode =>
      component == that.component && // Include component function
      props == that.props &&
      key == that.key
    // Note: component function equality is not reliable
    case _ => false
  }

  def withKey(k: String): ComponentNode = copy(key = Some(k))
}

case class TextNode(
    text: String,
    parent: Option[FluxusNode],
    var domNode: Option[Node],
) extends FluxusNode {
  val key: Option[String] = None

  override def hashCode: Int = {
    val prime  = 31
    var result = 1
    result = prime * result + text.hashCode
    result = prime * result + key.hashCode
    result
  }

  override def equals(other: Any): Boolean = other match {
    case that: TextNode =>
      text == that.text &&
      key == that.key
    case _ => false
  }
}
