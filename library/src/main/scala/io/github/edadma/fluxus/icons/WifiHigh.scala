package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/wifi-high", JSImport.Default)
private object WifiHighIcon extends js.Array[js.Any]

def WifiHigh(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(WifiHighIcon, color, size)
