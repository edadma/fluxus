package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/fuel", JSImport.Default)
private object FuelIcon extends js.Array[js.Any]

def Fuel(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FuelIcon, color, size)
