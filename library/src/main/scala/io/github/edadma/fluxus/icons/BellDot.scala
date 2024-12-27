package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/bell-dot", JSImport.Default)
private object BellDotIcon extends js.Array[js.Any]

def BellDot(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BellDotIcon, color, size)
