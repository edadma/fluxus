package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/house", JSImport.Default)
private object HouseIcon extends js.Array[js.Any]

def House(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(HouseIcon, color, size)
