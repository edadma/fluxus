package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/ice-cream-cone", JSImport.Default)
private object IceCreamConeIcon extends js.Array[js.Any]

def IceCreamCone(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(IceCreamConeIcon, color, size)
