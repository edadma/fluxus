package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/map-pin-x", JSImport.Default)
private object MapPinXIcon extends js.Array[js.Any]

def MapPinX(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MapPinXIcon, color, size)
