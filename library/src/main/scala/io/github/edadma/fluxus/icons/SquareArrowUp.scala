package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/square-arrow-up", JSImport.Default)
private object SquareArrowUpIcon extends js.Array[js.Any]

def SquareArrowUp(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SquareArrowUpIcon, color, size)
