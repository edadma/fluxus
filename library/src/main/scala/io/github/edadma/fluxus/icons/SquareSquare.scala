package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/square-square", JSImport.Default)
private object SquareSquareIcon extends js.Array[js.Any]

def SquareSquare(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SquareSquareIcon, color, size)
