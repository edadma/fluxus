package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/gauge", JSImport.Default)
private object GaugeIcon extends js.Array[js.Any]

def Gauge(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(GaugeIcon, color, size)
