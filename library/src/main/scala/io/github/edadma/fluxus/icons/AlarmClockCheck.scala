package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/alarm-clock-check", JSImport.Default)
private object AlarmClockCheckIcon extends js.Array[js.Any]

def AlarmClockCheck(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(AlarmClockCheckIcon, color, size)
