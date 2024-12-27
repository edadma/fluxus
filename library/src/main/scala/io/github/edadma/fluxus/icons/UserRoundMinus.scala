package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/user-round-minus", JSImport.Default)
private object UserRoundMinusIcon extends js.Array[js.Any]

def UserRoundMinus(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(UserRoundMinusIcon, color, size)
