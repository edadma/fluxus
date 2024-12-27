package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/telescope", JSImport.Default)
private object TelescopeIcon extends js.Array[js.Any]

def Telescope(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TelescopeIcon, color, size)
