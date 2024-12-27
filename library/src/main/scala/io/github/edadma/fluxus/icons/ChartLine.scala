package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/chart-line", JSImport.Default)
private object ChartLineIcon extends js.Array[js.Any]

def ChartLine(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ChartLineIcon, color, size)
