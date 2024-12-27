package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/square-x", JSImport.Default)
private object SquareXIcon extends js.Array[js.Any]

def SquareX(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SquareXIcon, color, size)
