package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/pointer-off", JSImport.Default)
private object PointerOffIcon extends js.Array[js.Any]

def PointerOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PointerOffIcon, color, size)
