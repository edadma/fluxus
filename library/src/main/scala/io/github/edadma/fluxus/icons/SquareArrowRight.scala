package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/square-arrow-right", JSImport.Default)
private object SquareArrowRightIcon extends js.Array[js.Any]

def SquareArrowRight(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SquareArrowRightIcon, color, size)
