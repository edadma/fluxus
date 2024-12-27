package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/rotate-cw-square", JSImport.Default)
private object RotateCwSquareIcon extends js.Array[js.Any]

def RotateCwSquare(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(RotateCwSquareIcon, color, size)
