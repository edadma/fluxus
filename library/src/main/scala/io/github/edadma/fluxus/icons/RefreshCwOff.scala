package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/refresh-cw-off", JSImport.Default)
private object RefreshCwOffIcon extends js.Array[js.Any]

def RefreshCwOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(RefreshCwOffIcon, color, size)
