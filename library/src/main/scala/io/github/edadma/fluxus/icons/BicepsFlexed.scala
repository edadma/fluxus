package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/biceps-flexed", JSImport.Default)
private object BicepsFlexedIcon extends js.Array[js.Any]

def BicepsFlexed(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BicepsFlexedIcon, color, size)
