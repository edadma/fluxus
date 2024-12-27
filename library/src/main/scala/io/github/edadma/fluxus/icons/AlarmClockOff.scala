package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/alarm-clock-off", JSImport.Default)
private object AlarmClockOffIcon extends js.Array[js.Any]

def AlarmClockOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(AlarmClockOffIcon, color, size)
