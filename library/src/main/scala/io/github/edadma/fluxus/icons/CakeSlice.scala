package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/cake-slice", JSImport.Default)
private object CakeSliceIcon extends js.Array[js.Any]

def CakeSlice(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CakeSliceIcon, color, size)
