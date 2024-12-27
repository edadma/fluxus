package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/battery-low", JSImport.Default)
private object BatteryLowIcon extends js.Array[js.Any]

def BatteryLow(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BatteryLowIcon, color, size)
