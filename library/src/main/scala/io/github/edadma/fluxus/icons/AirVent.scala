package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/air-vent", JSImport.Default)
private object AirVentIcon extends js.Array[js.Any]

def AirVent(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(AirVentIcon, color, size)
