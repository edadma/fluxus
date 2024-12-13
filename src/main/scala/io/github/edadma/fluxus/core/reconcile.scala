package io.github.edadma.fluxus.core

import io.github.edadma.fluxus.{ElementNode, FluxusNode, TextNode, ComponentNode, logger}
import org.scalajs.dom
import org.scalajs.dom.{Element, Node}

def reconcile(oldNode: Option[FluxusNode], newNode: Option[FluxusNode], container: dom.Element): Unit = {
  val operations = diff(oldNode, newNode)
  operations.foreach(op => commit(op, container))
}
