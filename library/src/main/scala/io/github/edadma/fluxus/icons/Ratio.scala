package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/ratio", JSImport.Default)
private object RatioIcon extends js.Array[js.Any]

def Ratio(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(RatioIcon, color, size)
