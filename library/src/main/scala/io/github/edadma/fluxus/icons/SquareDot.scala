package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/square-dot", JSImport.Default)
private object SquareDotIcon extends js.Array[js.Any]

def SquareDot(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SquareDotIcon, color, size)
