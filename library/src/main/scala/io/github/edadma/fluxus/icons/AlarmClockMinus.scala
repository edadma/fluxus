package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/alarm-clock-minus", JSImport.Default)
private object AlarmClockMinusIcon extends js.Array[js.Any]

def AlarmClockMinus(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(AlarmClockMinusIcon, color, size)
