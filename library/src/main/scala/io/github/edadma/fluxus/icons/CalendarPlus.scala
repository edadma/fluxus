package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/calendar-plus", JSImport.Default)
private object CalendarPlusIcon extends js.Array[js.Any]

def CalendarPlus(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CalendarPlusIcon, color, size)
