package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/user-plus", JSImport.Default)
private object UserPlusIcon extends js.Array[js.Any]

def UserPlus(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(UserPlusIcon, color, size)
