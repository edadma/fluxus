package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/square-arrow-up-right", JSImport.Default)
private object SquareArrowUpRightIcon extends js.Array[js.Any]

def SquareArrowUpRight(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SquareArrowUpRightIcon, color, size)
