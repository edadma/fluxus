package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/satellite", JSImport.Default)
private object SatelliteIcon extends js.Array[js.Any]

def Satellite(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SatelliteIcon, color, size)
