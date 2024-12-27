package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/refresh-cw", JSImport.Default)
private object RefreshCwIcon extends js.Array[js.Any]

def RefreshCw(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(RefreshCwIcon, color, size)
