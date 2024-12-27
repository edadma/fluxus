package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/clock-arrow-up", JSImport.Default)
private object ClockArrowUpIcon extends js.Array[js.Any]

def ClockArrowUp(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ClockArrowUpIcon, color, size)
