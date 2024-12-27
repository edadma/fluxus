package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/square-split-vertical", JSImport.Default)
private object SquareSplitVerticalIcon extends js.Array[js.Any]

def SquareSplitVertical(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SquareSplitVerticalIcon, color, size)
