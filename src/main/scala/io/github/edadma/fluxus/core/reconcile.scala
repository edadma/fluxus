package io.github.edadma.fluxus.core

import io.github.edadma.fluxus.FluxusNode
import org.scalajs.dom

def reconcile(oldNode: Option[FluxusNode], newNode: Option[FluxusNode], container: dom.Element): Unit = {
  val operations = diff(oldNode, newNode)

  commit(operations, container)
}
