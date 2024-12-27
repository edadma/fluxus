package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/map-pin-x-inside", JSImport.Default)
private object MapPinXInsideIcon extends js.Array[js.Any]

def MapPinXInside(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MapPinXInsideIcon, color, size)
