package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/file-chart-pie", JSImport.Default)
private object FileChartPieIcon extends js.Array[js.Any]

def FileChartPie(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FileChartPieIcon, color, size)
