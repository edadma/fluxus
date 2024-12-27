package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/list-minus", JSImport.Default)
private object ListMinusIcon extends js.Array[js.Any]

def ListMinus(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ListMinusIcon, color, size)
