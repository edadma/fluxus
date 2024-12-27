package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/cog", JSImport.Default)
private object CogIcon extends js.Array[js.Any]

def Cog(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CogIcon, color, size)
