package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/user-cog", JSImport.Default)
private object UserCogIcon extends js.Array[js.Any]

def UserCog(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(UserCogIcon, color, size)
