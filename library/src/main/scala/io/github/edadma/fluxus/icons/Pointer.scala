package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/pointer", JSImport.Default)
private object PointerIcon extends js.Array[js.Any]

def Pointer(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PointerIcon, color, size)
