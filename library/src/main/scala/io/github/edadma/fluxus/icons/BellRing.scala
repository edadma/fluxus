package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/bell-ring", JSImport.Default)
private object BellRingIcon extends js.Array[js.Any]

def BellRing(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BellRingIcon, color, size)
