package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/calendar-x", JSImport.Default)
private object CalendarXIcon extends js.Array[js.Any]

def CalendarX(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CalendarXIcon, color, size)
