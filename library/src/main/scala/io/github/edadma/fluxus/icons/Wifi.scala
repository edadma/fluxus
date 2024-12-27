package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/wifi", JSImport.Default)
private object WifiIcon extends js.Array[js.Any]

def Wifi(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(WifiIcon, color, size)
