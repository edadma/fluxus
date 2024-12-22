package io.github.edadma.fluxus

import io.github.edadma.fluxus.core.createDOM

import org.scalajs.dom.{Element, document}

def render(app: => FluxusNode, container: Element): Unit = createDOM(noPropsComponentNode(() => app), container)

def render(app: => FluxusNode, id: String): Unit = render(app, document.getElementById("app"))
