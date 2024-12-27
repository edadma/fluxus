package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/land-plot", JSImport.Default)
private object LandPlotIcon extends js.Array[js.Any]

def LandPlot(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(LandPlotIcon, color, size)
