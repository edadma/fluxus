package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/maximize", JSImport.Default)
private object MaximizeIcon extends js.Array[js.Any]

def Maximize(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MaximizeIcon, color, size)
