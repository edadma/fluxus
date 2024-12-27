package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/chart-no-axes-column-decreasing", JSImport.Default)
private object ChartNoAxesColumnDecreasingIcon extends js.Array[js.Any]

def ChartNoAxesColumnDecreasing(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ChartNoAxesColumnDecreasingIcon, color, size)
