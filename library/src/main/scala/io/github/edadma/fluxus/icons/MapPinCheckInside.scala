package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/map-pin-check-inside", JSImport.Default)
private object MapPinCheckInsideIcon extends js.Array[js.Any]

def MapPinCheckInside(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MapPinCheckInsideIcon, color, size)
