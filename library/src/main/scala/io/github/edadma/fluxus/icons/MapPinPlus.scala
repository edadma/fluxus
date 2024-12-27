package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/map-pin-plus", JSImport.Default)
private object MapPinPlusIcon extends js.Array[js.Any]

def MapPinPlus(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MapPinPlusIcon, color, size)
