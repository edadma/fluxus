package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/chart-bar-stacked", JSImport.Default)
private object ChartBarStackedIcon extends js.Array[js.Any]

def ChartBarStacked(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ChartBarStackedIcon, color, size)
