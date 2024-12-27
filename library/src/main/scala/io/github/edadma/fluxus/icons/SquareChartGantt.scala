package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/square-chart-gantt", JSImport.Default)
private object SquareChartGanttIcon extends js.Array[js.Any]

def SquareChartGantt(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SquareChartGanttIcon, color, size)
