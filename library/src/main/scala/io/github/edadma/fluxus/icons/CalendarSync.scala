package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/calendar-sync", JSImport.Default)
private object CalendarSyncIcon extends js.Array[js.Any]

def CalendarSync(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CalendarSyncIcon, color, size)
