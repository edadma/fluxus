package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/calendar-heart", JSImport.Default)
private object CalendarHeartIcon extends js.Array[js.Any]

def CalendarHeart(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CalendarHeartIcon, color, size)
