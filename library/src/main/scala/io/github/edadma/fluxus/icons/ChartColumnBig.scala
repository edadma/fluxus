package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/chart-column-big", JSImport.Default)
private object ChartColumnBigIcon extends js.Array[js.Any]

def ChartColumnBig(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ChartColumnBigIcon, color, size)
