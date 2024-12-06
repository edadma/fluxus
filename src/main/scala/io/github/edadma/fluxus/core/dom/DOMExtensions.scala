package io.github.edadma.fluxus.core.dom

import io.github.edadma.fluxus.core.types.FluxusNode
import org.scalajs.dom
import scala.scalajs.js

@js.native
trait FluxusExtension extends js.Object {
  var fluxusNode: js.UndefOr[FluxusNode] = js.native
}

object DOMExtensions {
  implicit class RichElement(val element: dom.Element) extends AnyVal {
    def getFluxusNode: Option[FluxusNode] = {
      Option(element.asInstanceOf[FluxusExtension].fluxusNode.toOption).flatten
    }

    def setFluxusNode(node: FluxusNode): Unit = {
      element.asInstanceOf[FluxusExtension].fluxusNode = node
    }
  }
}
