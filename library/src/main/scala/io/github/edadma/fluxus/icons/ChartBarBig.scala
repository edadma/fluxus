package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/chart-bar-big", JSImport.Default)
private object ChartBarBigIcon extends js.Array[js.Any]

def ChartBarBig(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ChartBarBigIcon, color, size)
