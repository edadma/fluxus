package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/hammer", JSImport.Default)
private object HammerIcon extends js.Array[js.Any]

def Hammer(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(HammerIcon, color, size)
