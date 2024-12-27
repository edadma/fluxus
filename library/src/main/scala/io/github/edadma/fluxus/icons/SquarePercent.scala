package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/square-percent", JSImport.Default)
private object SquarePercentIcon extends js.Array[js.Any]

def SquarePercent(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SquarePercentIcon, color, size)
