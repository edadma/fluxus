package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/map-pin-plus-inside", JSImport.Default)
private object MapPinPlusInsideIcon extends js.Array[js.Any]

def MapPinPlusInside(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MapPinPlusInsideIcon, color, size)
