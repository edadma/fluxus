package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/cooking-pot", JSImport.Default)
private object CookingPotIcon extends js.Array[js.Any]

def CookingPot(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CookingPotIcon, color, size)
