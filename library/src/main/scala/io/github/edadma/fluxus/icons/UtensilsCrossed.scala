package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/utensils-crossed", JSImport.Default)
private object UtensilsCrossedIcon extends js.Array[js.Any]

def UtensilsCrossed(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(UtensilsCrossedIcon, color, size)
