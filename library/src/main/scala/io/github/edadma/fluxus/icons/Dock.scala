package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/dock", JSImport.Default)
private object DockIcon extends js.Array[js.Any]

def Dock(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(DockIcon, color, size)
