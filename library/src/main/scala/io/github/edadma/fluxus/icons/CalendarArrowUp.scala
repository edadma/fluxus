package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/calendar-arrow-up", JSImport.Default)
private object CalendarArrowUpIcon extends js.Array[js.Any]

def CalendarArrowUp(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CalendarArrowUpIcon, color, size)
