package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/user-x", JSImport.Default)
private object UserXIcon extends js.Array[js.Any]

def UserX(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(UserXIcon, color, size)
