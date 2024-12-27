package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/calendar-check-2", JSImport.Default)
private object CalendarCheck2Icon extends js.Array[js.Any]

def CalendarCheck2(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CalendarCheck2Icon, color, size)
