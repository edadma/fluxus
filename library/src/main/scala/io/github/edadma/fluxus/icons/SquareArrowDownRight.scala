package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/square-arrow-down-right", JSImport.Default)
private object SquareArrowDownRightIcon extends js.Array[js.Any]

def SquareArrowDownRight(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SquareArrowDownRightIcon, color, size)
