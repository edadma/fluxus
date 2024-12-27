package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/alarm-clock", JSImport.Default)
private object AlarmClockIcon extends js.Array[js.Any]

def AlarmClock(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(AlarmClockIcon, color, size)
