package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/diamond", JSImport.Default)
private object DiamondIcon extends js.Array[js.Any]

def Diamond(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(DiamondIcon, color, size)
