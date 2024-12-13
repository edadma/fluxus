package io.github.edadma.fluxus

import io.github.edadma.fluxus.core.createDOM

import org.scalajs.dom.{Element, document}

def render(root: => FluxusNode, container: Element): Unit = createDOM(root, container)

def render(root: => FluxusNode, id: String): Unit = render(root, document.getElementById("app"))
