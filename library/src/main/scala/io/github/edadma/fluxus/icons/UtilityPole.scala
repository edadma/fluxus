package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/utility-pole", JSImport.Default)
private object UtilityPoleIcon extends js.Array[js.Any]

def UtilityPole(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(UtilityPoleIcon, color, size)
