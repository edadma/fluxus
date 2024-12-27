package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/chart-column", JSImport.Default)
private object ChartColumnIcon extends js.Array[js.Any]

def ChartColumn(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ChartColumnIcon, color, size)
