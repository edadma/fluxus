package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/diamond-minus", JSImport.Default)
private object DiamondMinusIcon extends js.Array[js.Any]

def DiamondMinus(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(DiamondMinusIcon, color, size)
