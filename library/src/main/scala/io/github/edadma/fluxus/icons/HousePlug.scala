package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/house-plug", JSImport.Default)
private object HousePlugIcon extends js.Array[js.Any]

def HousePlug(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(HousePlugIcon, color, size)
