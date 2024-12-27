package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/chart-gantt", JSImport.Default)
private object ChartGanttIcon extends js.Array[js.Any]

def ChartGantt(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ChartGanttIcon, color, size)
