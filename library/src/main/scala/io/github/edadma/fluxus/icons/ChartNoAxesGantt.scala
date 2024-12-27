package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/chart-no-axes-gantt", JSImport.Default)
private object ChartNoAxesGanttIcon extends js.Array[js.Any]

def ChartNoAxesGantt(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ChartNoAxesGanttIcon, color, size)
