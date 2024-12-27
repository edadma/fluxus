package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/square-arrow-down", JSImport.Default)
private object SquareArrowDownIcon extends js.Array[js.Any]

def SquareArrowDown(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SquareArrowDownIcon, color, size)
