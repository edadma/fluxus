package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/between-vertical-end", JSImport.Default)
private object BetweenVerticalEndIcon extends js.Array[js.Any]

def BetweenVerticalEnd(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BetweenVerticalEndIcon, color, size)
