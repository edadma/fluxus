package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/hop-off", JSImport.Default)
private object HopOffIcon extends js.Array[js.Any]

def HopOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(HopOffIcon, color, size)
