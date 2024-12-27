package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/chart-area", JSImport.Default)
private object ChartAreaIcon extends js.Array[js.Any]

def ChartArea(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ChartAreaIcon, color, size)
