package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/dices", JSImport.Default)
private object DicesIcon extends js.Array[js.Any]

def Dices(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(DicesIcon, color, size)
