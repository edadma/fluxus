package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/radiation", JSImport.Default)
private object RadiationIcon extends js.Array[js.Any]

def Radiation(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(RadiationIcon, color, size)
