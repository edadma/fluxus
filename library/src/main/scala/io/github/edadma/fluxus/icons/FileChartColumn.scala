package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/file-chart-column", JSImport.Default)
private object FileChartColumnIcon extends js.Array[js.Any]

def FileChartColumn(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FileChartColumnIcon, color, size)
