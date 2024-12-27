package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/chart-bar-increasing", JSImport.Default)
private object ChartBarIncreasingIcon extends js.Array[js.Any]

def ChartBarIncreasing(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ChartBarIncreasingIcon, color, size)
