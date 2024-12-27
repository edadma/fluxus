package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/square-dashed-mouse-pointer", JSImport.Default)
private object SquareDashedMousePointerIcon extends js.Array[js.Any]

def SquareDashedMousePointer(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SquareDashedMousePointerIcon, color, size)
