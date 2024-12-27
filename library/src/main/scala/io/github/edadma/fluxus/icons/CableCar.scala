package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/cable-car", JSImport.Default)
private object CableCarIcon extends js.Array[js.Any]

def CableCar(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CableCarIcon, color, size)
