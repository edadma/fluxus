package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/square-menu", JSImport.Default)
private object SquareMenuIcon extends js.Array[js.Any]

def SquareMenu(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SquareMenuIcon, color, size)
