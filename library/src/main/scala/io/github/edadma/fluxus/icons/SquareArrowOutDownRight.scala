package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/square-arrow-out-down-right", JSImport.Default)
private object SquareArrowOutDownRightIcon extends js.Array[js.Any]

def SquareArrowOutDownRight(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SquareArrowOutDownRightIcon, color, size)
