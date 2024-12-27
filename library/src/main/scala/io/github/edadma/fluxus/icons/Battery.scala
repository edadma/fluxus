package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/battery", JSImport.Default)
private object BatteryIcon extends js.Array[js.Any]

def Battery(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BatteryIcon, color, size)
