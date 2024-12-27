package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/calendar-off", JSImport.Default)
private object CalendarOffIcon extends js.Array[js.Any]

def CalendarOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CalendarOffIcon, color, size)
