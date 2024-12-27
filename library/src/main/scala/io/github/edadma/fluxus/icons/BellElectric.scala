package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/bell-electric", JSImport.Default)
private object BellElectricIcon extends js.Array[js.Any]

def BellElectric(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BellElectricIcon, color, size)
