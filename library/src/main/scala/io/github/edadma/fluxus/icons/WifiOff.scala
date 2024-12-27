package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/wifi-off", JSImport.Default)
private object WifiOffIcon extends js.Array[js.Any]

def WifiOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(WifiOffIcon, color, size)
