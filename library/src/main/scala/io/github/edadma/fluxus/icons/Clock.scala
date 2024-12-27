package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/clock", JSImport.Default)
private object ClockIcon extends js.Array[js.Any]

def Clock(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ClockIcon, color, size)
