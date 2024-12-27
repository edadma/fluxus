package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/milk-off", JSImport.Default)
private object MilkOffIcon extends js.Array[js.Any]

def MilkOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MilkOffIcon, color, size)
