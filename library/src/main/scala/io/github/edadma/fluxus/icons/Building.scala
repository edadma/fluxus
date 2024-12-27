package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/building", JSImport.Default)
private object BuildingIcon extends js.Array[js.Any]

def Building(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BuildingIcon, color, size)
