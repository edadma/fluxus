package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/square-split-horizontal", JSImport.Default)
private object SquareSplitHorizontalIcon extends js.Array[js.Any]

def SquareSplitHorizontal(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SquareSplitHorizontalIcon, color, size)
