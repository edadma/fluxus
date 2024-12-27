package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/wine-off", JSImport.Default)
private object WineOffIcon extends js.Array[js.Any]

def WineOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(WineOffIcon, color, size)
