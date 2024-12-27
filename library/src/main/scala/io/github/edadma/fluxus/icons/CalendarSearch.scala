package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/calendar-search", JSImport.Default)
private object CalendarSearchIcon extends js.Array[js.Any]

def CalendarSearch(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CalendarSearchIcon, color, size)
