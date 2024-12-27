package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/chart-column-stacked", JSImport.Default)
private object ChartColumnStackedIcon extends js.Array[js.Any]

def ChartColumnStacked(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ChartColumnStackedIcon, color, size)
