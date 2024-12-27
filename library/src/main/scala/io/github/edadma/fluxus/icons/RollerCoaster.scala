package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/roller-coaster", JSImport.Default)
private object RollerCoasterIcon extends js.Array[js.Any]

def RollerCoaster(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(RollerCoasterIcon, color, size)
