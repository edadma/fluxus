package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/square-arrow-up-left", JSImport.Default)
private object SquareArrowUpLeftIcon extends js.Array[js.Any]

def SquareArrowUpLeft(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SquareArrowUpLeftIcon, color, size)
