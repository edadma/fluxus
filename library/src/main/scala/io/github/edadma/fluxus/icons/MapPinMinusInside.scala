package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/map-pin-minus-inside", JSImport.Default)
private object MapPinMinusInsideIcon extends js.Array[js.Any]

def MapPinMinusInside(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MapPinMinusInsideIcon, color, size)
