package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/rotate-ccw-square", JSImport.Default)
private object RotateCcwSquareIcon extends js.Array[js.Any]

def RotateCcwSquare(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(RotateCcwSquareIcon, color, size)
