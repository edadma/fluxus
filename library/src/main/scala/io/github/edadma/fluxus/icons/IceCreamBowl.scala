package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/ice-cream-bowl", JSImport.Default)
private object IceCreamBowlIcon extends js.Array[js.Any]

def IceCreamBowl(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(IceCreamBowlIcon, color, size)
