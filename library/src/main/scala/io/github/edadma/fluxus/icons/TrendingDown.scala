package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/trending-down", JSImport.Default)
private object TrendingDownIcon extends js.Array[js.Any]

def TrendingDown(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TrendingDownIcon, color, size)
