package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/satellite-dish", JSImport.Default)
private object SatelliteDishIcon extends js.Array[js.Any]

def SatelliteDish(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SatelliteDishIcon, color, size)
