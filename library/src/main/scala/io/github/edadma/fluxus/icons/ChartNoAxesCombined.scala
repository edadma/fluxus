package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/chart-no-axes-combined", JSImport.Default)
private object ChartNoAxesCombinedIcon extends js.Array[js.Any]

def ChartNoAxesCombined(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ChartNoAxesCombinedIcon, color, size)
