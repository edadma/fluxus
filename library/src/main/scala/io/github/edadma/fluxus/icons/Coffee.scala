package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/coffee", JSImport.Default)
private object CoffeeIcon extends js.Array[js.Any]

def Coffee(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CoffeeIcon, color, size)
