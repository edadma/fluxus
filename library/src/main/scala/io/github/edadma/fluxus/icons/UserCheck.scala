package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/user-check", JSImport.Default)
private object UserCheckIcon extends js.Array[js.Any]

def UserCheck(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(UserCheckIcon, color, size)
