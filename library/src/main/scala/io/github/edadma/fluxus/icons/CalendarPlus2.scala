package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/calendar-plus-2", JSImport.Default)
private object CalendarPlus2Icon extends js.Array[js.Any]

def CalendarPlus2(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CalendarPlus2Icon, color, size)
