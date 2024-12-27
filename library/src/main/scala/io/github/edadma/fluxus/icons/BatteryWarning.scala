package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/battery-warning", JSImport.Default)
private object BatteryWarningIcon extends js.Array[js.Any]

def BatteryWarning(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BatteryWarningIcon, color, size)
