package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/battery-full", JSImport.Default)
private object BatteryFullIcon extends js.Array[js.Any]

def BatteryFull(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BatteryFullIcon, color, size)
