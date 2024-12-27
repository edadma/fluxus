package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/thermometer", JSImport.Default)
private object ThermometerIcon extends js.Array[js.Any]

def Thermometer(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ThermometerIcon, color, size)
