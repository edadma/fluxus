package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/candy-off", JSImport.Default)
private object CandyOffIcon extends js.Array[js.Any]

def CandyOff(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CandyOffIcon, color, size)
