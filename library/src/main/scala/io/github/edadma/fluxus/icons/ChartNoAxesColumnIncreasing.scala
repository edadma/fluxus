package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/chart-no-axes-column-increasing", JSImport.Default)
private object ChartNoAxesColumnIncreasingIcon extends js.Array[js.Any]

def ChartNoAxesColumnIncreasing(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ChartNoAxesColumnIncreasingIcon, color, size)
