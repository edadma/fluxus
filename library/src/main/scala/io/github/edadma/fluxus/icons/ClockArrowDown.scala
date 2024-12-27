package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/clock-arrow-down", JSImport.Default)
private object ClockArrowDownIcon extends js.Array[js.Any]

def ClockArrowDown(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ClockArrowDownIcon, color, size)
