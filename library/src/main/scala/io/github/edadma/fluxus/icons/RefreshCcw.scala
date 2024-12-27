package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/refresh-ccw", JSImport.Default)
private object RefreshCcwIcon extends js.Array[js.Any]

def RefreshCcw(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(RefreshCcwIcon, color, size)
