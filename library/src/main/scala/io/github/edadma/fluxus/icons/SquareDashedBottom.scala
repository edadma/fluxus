package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/square-dashed-bottom", JSImport.Default)
private object SquareDashedBottomIcon extends js.Array[js.Any]

def SquareDashedBottom(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SquareDashedBottomIcon, color, size)
