package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/square-check-big", JSImport.Default)
private object SquareCheckBigIcon extends js.Array[js.Any]

def SquareCheckBig(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SquareCheckBigIcon, color, size)
