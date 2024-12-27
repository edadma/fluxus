package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/move-diagonal", JSImport.Default)
private object MoveDiagonalIcon extends js.Array[js.Any]

def MoveDiagonal(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MoveDiagonalIcon, color, size)
