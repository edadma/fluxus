package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/navigation-2-off", JSImport.Default)
private object Navigation2OffIcon extends js.Array[js.Any]

def Navigation2Off(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Navigation2OffIcon, color, size)
