package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/minus", JSImport.Default)
private object MinusIcon extends js.Array[js.Any]

def Minus(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MinusIcon, color, size)
