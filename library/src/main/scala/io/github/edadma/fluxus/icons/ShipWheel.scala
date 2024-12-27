package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/ship-wheel", JSImport.Default)
private object ShipWheelIcon extends js.Array[js.Any]

def ShipWheel(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ShipWheelIcon, color, size)
