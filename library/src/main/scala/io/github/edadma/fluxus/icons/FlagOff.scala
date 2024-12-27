package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/flag-off", JSImport.Default)
private object FlagOffIcon extends js.Array[js.Any]

def FlagOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FlagOffIcon, color, size)
