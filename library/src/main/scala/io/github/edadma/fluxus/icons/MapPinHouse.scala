package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/map-pin-house", JSImport.Default)
private object MapPinHouseIcon extends js.Array[js.Any]

def MapPinHouse(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MapPinHouseIcon, color, size)
