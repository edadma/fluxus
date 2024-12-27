package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/square-arrow-out-down-left", JSImport.Default)
private object SquareArrowOutDownLeftIcon extends js.Array[js.Any]

def SquareArrowOutDownLeft(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SquareArrowOutDownLeftIcon, color, size)
