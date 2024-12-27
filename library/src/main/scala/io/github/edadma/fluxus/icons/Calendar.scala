package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/calendar", JSImport.Default)
private object CalendarIcon extends js.Array[js.Any]

def Calendar(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CalendarIcon, color, size)
