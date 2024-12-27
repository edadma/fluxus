package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/square-function", JSImport.Default)
private object SquareFunctionIcon extends js.Array[js.Any]

def SquareFunction(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SquareFunctionIcon, color, size)
