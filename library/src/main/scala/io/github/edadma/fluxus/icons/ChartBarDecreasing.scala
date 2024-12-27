package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/chart-bar-decreasing", JSImport.Default)
private object ChartBarDecreasingIcon extends js.Array[js.Any]

def ChartBarDecreasing(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ChartBarDecreasingIcon, color, size)
