package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/truck", JSImport.Default)
private object TruckIcon extends js.Array[js.Any]

def Truck(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TruckIcon, color, size)
