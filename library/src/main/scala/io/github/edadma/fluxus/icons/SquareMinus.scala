package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/square-minus", JSImport.Default)
private object SquareMinusIcon extends js.Array[js.Any]

def SquareMinus(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SquareMinusIcon, color, size)
