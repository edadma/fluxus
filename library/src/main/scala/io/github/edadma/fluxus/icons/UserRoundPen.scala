package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/user-round-pen", JSImport.Default)
private object UserRoundPenIcon extends js.Array[js.Any]

def UserRoundPen(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(UserRoundPenIcon, color, size)
