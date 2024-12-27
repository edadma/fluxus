package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/diamond-plus", JSImport.Default)
private object DiamondPlusIcon extends js.Array[js.Any]

def DiamondPlus(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(DiamondPlusIcon, color, size)
