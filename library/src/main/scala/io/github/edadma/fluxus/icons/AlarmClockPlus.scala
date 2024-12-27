package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/alarm-clock-plus", JSImport.Default)
private object AlarmClockPlusIcon extends js.Array[js.Any]

def AlarmClockPlus(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(AlarmClockPlusIcon, color, size)
