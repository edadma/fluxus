package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/square-bottom-dashed-scissors", JSImport.Default)
private object SquareBottomDashedScissorsIcon extends js.Array[js.Any]

def SquareBottomDashedScissors(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SquareBottomDashedScissorsIcon, color, size)
