package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/smartphone-charging", JSImport.Default)
private object SmartphoneChargingIcon extends js.Array[js.Any]

def SmartphoneCharging(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SmartphoneChargingIcon, color, size)
