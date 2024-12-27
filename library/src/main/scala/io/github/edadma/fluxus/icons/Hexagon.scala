package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/hexagon", JSImport.Default)
private object HexagonIcon extends js.Array[js.Any]

def Hexagon(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(HexagonIcon, color, size)
