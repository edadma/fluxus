package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/wifi-zero", JSImport.Default)
private object WifiZeroIcon extends js.Array[js.Any]

def WifiZero(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(WifiZeroIcon, color, size)
