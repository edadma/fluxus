package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/pizza", JSImport.Default)
private object PizzaIcon extends js.Array[js.Any]

def Pizza(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PizzaIcon, color, size)
