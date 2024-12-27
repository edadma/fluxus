package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/backpack", JSImport.Default)
private object BackpackIcon extends js.Array[js.Any]

def Backpack(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BackpackIcon, color, size)
