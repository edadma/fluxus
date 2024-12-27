package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/user-round-check", JSImport.Default)
private object UserRoundCheckIcon extends js.Array[js.Any]

def UserRoundCheck(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(UserRoundCheckIcon, color, size)
