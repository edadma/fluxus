package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/trending-up", JSImport.Default)
private object TrendingUpIcon extends js.Array[js.Any]

def TrendingUp(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TrendingUpIcon, color, size)
