package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/user-minus", JSImport.Default)
private object UserMinusIcon extends js.Array[js.Any]

def UserMinus(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(UserMinusIcon, color, size)
