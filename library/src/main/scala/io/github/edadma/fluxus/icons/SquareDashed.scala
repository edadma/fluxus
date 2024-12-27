package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/square-dashed", JSImport.Default)
private object SquareDashedIcon extends js.Array[js.Any]

def SquareDashed(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SquareDashedIcon, color, size)
