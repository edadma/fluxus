package io.github.edadma.fluxus.core

import io.github.edadma.fluxus.{ComponentNode, ElementNode, FluxusNode, TextNode}
import org.scalajs.dom

import scala.scalajs.js

sealed trait DOMOperation

case class RemoveProps(node: ElementNode, props: Set[String])                                     extends DOMOperation
case class AddProps(node: ElementNode, props: Map[String, Any])                                   extends DOMOperation
case class RemoveEvent(node: ElementNode, eventName: String)                                      extends DOMOperation
case class AddEvent(node: ElementNode, eventName: String, handler: js.Function1[dom.Event, Unit]) extends DOMOperation
case class Replace(oldNode: FluxusNode, newNode: FluxusNode)                                      extends DOMOperation
case class UpdateText(node: TextNode, newText: String)                                            extends DOMOperation
case class RemoveNode(node: FluxusNode)                                                           extends DOMOperation
case class InsertNode(node: FluxusNode, parentNode: FluxusNode, position: Option[Int] = None)     extends DOMOperation
case class RerenderComponent(old: ComponentNode, newProps: ComponentNode)                         extends DOMOperation
case class MoveNode(node: FluxusNode, toIndex: Int)                                               extends DOMOperation
case class UpdateProperties(node: ElementNode, properties: Map[String, Any])                      extends DOMOperation
