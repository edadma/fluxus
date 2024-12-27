package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/battery-medium", JSImport.Default)
private object BatteryMediumIcon extends js.Array[js.Any]

def BatteryMedium(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BatteryMediumIcon, color, size)
