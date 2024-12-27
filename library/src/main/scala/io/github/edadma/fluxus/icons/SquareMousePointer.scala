package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/square-mouse-pointer", JSImport.Default)
private object SquareMousePointerIcon extends js.Array[js.Any]

def SquareMousePointer(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SquareMousePointerIcon, color, size)
