package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/square-dashed-bottom-code", JSImport.Default)
private object SquareDashedBottomCodeIcon extends js.Array[js.Any]

def SquareDashedBottomCode(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SquareDashedBottomCodeIcon, color, size)
