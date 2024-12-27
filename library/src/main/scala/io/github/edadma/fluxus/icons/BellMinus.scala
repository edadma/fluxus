package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/bell-minus", JSImport.Default)
private object BellMinusIcon extends js.Array[js.Any]

def BellMinus(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BellMinusIcon, color, size)
