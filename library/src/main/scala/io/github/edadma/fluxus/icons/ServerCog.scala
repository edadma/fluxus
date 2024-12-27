package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/server-cog", JSImport.Default)
private object ServerCogIcon extends js.Array[js.Any]

def ServerCog(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ServerCogIcon, color, size)
