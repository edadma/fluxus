package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/traffic-cone", JSImport.Default)
private object TrafficConeIcon extends js.Array[js.Any]

def TrafficCone(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TrafficConeIcon, color, size)
