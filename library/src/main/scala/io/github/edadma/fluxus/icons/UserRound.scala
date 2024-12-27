package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/user-round", JSImport.Default)
private object UserRoundIcon extends js.Array[js.Any]

def UserRound(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(UserRoundIcon, color, size)
