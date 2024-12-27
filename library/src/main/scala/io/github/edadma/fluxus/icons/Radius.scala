package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/radius", JSImport.Default)
private object RadiusIcon extends js.Array[js.Any]

def Radius(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(RadiusIcon, color, size)
