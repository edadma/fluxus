package io.github.edadma.fluxus

import org.scalajs.dom.{Element, document}

def render(root: => FluxusNode, container: Element): Unit = ()

def render(root: => FluxusNode, id: String): Unit = render(root, document.getElementById("app"))
