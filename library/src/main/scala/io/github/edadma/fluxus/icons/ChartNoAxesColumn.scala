package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/chart-no-axes-column", JSImport.Default)
private object ChartNoAxesColumnIcon extends js.Array[js.Any]

def ChartNoAxesColumn(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ChartNoAxesColumnIcon, color, size)
