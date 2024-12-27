package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/percent", JSImport.Default)
private object PercentIcon extends js.Array[js.Any]

def Percent(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PercentIcon, color, size)
