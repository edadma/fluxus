package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/map-pin-check", JSImport.Default)
private object MapPinCheckIcon extends js.Array[js.Any]

def MapPinCheck(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MapPinCheckIcon, color, size)
