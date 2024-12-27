package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/bell-off", JSImport.Default)
private object BellOffIcon extends js.Array[js.Any]

def BellOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BellOffIcon, color, size)
