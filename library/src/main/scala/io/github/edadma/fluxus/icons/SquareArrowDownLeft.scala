package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/square-arrow-down-left", JSImport.Default)
private object SquareArrowDownLeftIcon extends js.Array[js.Any]

def SquareArrowDownLeft(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SquareArrowDownLeftIcon, color, size)
