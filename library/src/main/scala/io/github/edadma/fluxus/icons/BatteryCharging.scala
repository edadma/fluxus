package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/battery-charging", JSImport.Default)
private object BatteryChargingIcon extends js.Array[js.Any]

def BatteryCharging(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BatteryChargingIcon, color, size)
