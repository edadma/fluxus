package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/save-off", JSImport.Default)
private object SaveOffIcon extends js.Array[js.Any]

def SaveOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SaveOffIcon, color, size)
