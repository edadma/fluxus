package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/chart-column-increasing", JSImport.Default)
private object ChartColumnIncreasingIcon extends js.Array[js.Any]

def ChartColumnIncreasing(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ChartColumnIncreasingIcon, color, size)
