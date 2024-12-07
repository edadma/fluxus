package io.github.edadma.fluxus.api

import io.github.edadma.fluxus.core.dom.DOMOperations
import io.github.edadma.fluxus.core.types.FluxusNode
import org.scalajs.dom.{Element, document}

def renderApp(root: FluxusNode, container: Element): Unit = DOMOperations.mount(root, container)

def renderApp(root: FluxusNode, id: String): Unit = renderApp(root, document.getElementById("app"))
