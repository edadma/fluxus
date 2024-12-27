package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/refresh-ccw-dot", JSImport.Default)
private object RefreshCcwDotIcon extends js.Array[js.Any]

def RefreshCcwDot(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(RefreshCcwDotIcon, color, size)
