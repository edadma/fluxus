package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/chart-bar", JSImport.Default)
private object ChartBarIcon extends js.Array[js.Any]

def ChartBar(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ChartBarIcon, color, size)
