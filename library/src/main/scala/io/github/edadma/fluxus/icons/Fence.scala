package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/fence", JSImport.Default)
private object FenceIcon extends js.Array[js.Any]

def Fence(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FenceIcon, color, size)
