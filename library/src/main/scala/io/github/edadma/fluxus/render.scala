package io.github.edadma.fluxus

import io.github.edadma.fluxus.core.{BatchScheduler, createDOM}
import org.scalajs.dom.{Element, document}

def render(app: => FluxusNode, container: Element): Unit = {
  // Create initial DOM tree
  createDOM(noPropsComponentNode(() => app), container)

  // Now that entire tree is mounted, process effects
  BatchScheduler.flushEffects()
}

def render(app: => FluxusNode, id: String): Unit = render(app, document.getElementById("app"))
