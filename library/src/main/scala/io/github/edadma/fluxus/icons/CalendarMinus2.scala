package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/calendar-minus-2", JSImport.Default)
private object CalendarMinus2Icon extends js.Array[js.Any]

def CalendarMinus2(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CalendarMinus2Icon, color, size)
