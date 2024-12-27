package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/file-chart-line", JSImport.Default)
private object FileChartLineIcon extends js.Array[js.Any]

def FileChartLine(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FileChartLineIcon, color, size)
