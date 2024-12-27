package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/chart-network", JSImport.Default)
private object ChartNetworkIcon extends js.Array[js.Any]

def ChartNetwork(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ChartNetworkIcon, color, size)
