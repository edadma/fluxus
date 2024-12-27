package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/square-m", JSImport.Default)
private object SquareMIcon extends js.Array[js.Any]

def SquareM(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SquareMIcon, color, size)
