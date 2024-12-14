package io.github.edadma.fluxus.core

import io.github.edadma.fluxus.{ComponentNode, ElementNode, FluxusNode, TextNode}
import org.scalajs.dom

import scala.scalajs.js

sealed trait DOMOperation

object DOMOperation {
  case class Replace(oldNode: FluxusNode, newNode: FluxusNode) extends DOMOperation
  case class UpdateText(node: TextNode, newText: String)       extends DOMOperation
  case class UpdateProps(
      node: ElementNode,
      propsToRemove: Set[String],
      propsToAdd: Map[String, Any],
      eventsToRemove: Set[String],
      eventsToAdd: Map[String, js.Function1[dom.Event, Unit]],
  ) extends DOMOperation
  case class RemoveNode(node: FluxusNode)                                   extends DOMOperation
  case class InsertNode(node: FluxusNode, position: Option[Int] = None)     extends DOMOperation
  case class RerenderComponent(old: ComponentNode, newProps: ComponentNode) extends DOMOperation
}
