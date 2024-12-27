package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/clapperboard", JSImport.Default)
private object ClapperboardIcon extends js.Array[js.Any]

def Clapperboard(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ClapperboardIcon, color, size)
