package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/wifi-low", JSImport.Default)
private object WifiLowIcon extends js.Array[js.Any]

def WifiLow(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(WifiLowIcon, color, size)
