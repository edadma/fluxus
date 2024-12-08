package io.github.edadma.fluxus

import io.github.edadma.fluxus.core.{Component, FluxusNode}
import io.github.edadma.fluxus.core.dom.DOMOperations
import io.github.edadma.fluxus.logging.Logger
import org.scalajs.dom.{Element, document}

def renderApp(root: => FluxusNode, container: Element): Unit =
  val component =
    Component.create(
      render = _ => root,
      props = noProps,
      key = None,
      opId = Logger.nextOperationId,
      name = Some("root"),
    )

  DOMOperations.mount(component, container)

def renderApp(root: => FluxusNode, id: String): Unit = renderApp(root, document.getElementById("app"))
