package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/calendar-fold", JSImport.Default)
private object CalendarFoldIcon extends js.Array[js.Any]

def CalendarFold(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CalendarFoldIcon, color, size)
