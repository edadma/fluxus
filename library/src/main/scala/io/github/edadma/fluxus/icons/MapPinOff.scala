package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/map-pin-off", JSImport.Default)
private object MapPinOffIcon extends js.Array[js.Any]

def MapPinOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MapPinOffIcon, color, size)
