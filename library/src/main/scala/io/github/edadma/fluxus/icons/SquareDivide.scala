package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/square-divide", JSImport.Default)
private object SquareDivideIcon extends js.Array[js.Any]

def SquareDivide(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SquareDivideIcon, color, size)
