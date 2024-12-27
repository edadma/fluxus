package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/square-arrow-out-up-left", JSImport.Default)
private object SquareArrowOutUpLeftIcon extends js.Array[js.Any]

def SquareArrowOutUpLeft(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SquareArrowOutUpLeftIcon, color, size)
