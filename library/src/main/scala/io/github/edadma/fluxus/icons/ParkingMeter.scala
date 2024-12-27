package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/parking-meter", JSImport.Default)
private object ParkingMeterIcon extends js.Array[js.Any]

def ParkingMeter(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ParkingMeterIcon, color, size)
