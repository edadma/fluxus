package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/dumbbell", JSImport.Default)
private object DumbbellIcon extends js.Array[js.Any]

def Dumbbell(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(DumbbellIcon, color, size)
