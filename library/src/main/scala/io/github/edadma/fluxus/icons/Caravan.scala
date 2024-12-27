package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/caravan", JSImport.Default)
private object CaravanIcon extends js.Array[js.Any]

def Caravan(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CaravanIcon, color, size)
