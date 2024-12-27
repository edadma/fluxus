package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/link-2-off", JSImport.Default)
private object Link2OffIcon extends js.Array[js.Any]

def Link2Off(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Link2OffIcon, color, size)
