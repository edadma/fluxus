package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/square-code", JSImport.Default)
private object SquareCodeIcon extends js.Array[js.Any]

def SquareCode(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SquareCodeIcon, color, size)
