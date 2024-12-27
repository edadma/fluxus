package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/trending-up-down", JSImport.Default)
private object TrendingUpDownIcon extends js.Array[js.Any]

def TrendingUpDown(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TrendingUpDownIcon, color, size)
