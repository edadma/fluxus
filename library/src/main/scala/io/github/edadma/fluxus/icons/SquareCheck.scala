package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/square-check", JSImport.Default)
private object SquareCheckIcon extends js.Array[js.Any]

def SquareCheck(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SquareCheckIcon, color, size)
