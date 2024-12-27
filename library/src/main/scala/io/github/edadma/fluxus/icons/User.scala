package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/user", JSImport.Default)
private object UserIcon extends js.Array[js.Any]

def User(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(UserIcon, color, size)
