package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/hop", JSImport.Default)
private object HopIcon extends js.Array[js.Any]

def Hop(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(HopIcon, color, size)
