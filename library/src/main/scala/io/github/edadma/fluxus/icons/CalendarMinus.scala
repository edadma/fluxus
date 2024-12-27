package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/calendar-minus", JSImport.Default)
private object CalendarMinusIcon extends js.Array[js.Any]

def CalendarMinus(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CalendarMinusIcon, color, size)
