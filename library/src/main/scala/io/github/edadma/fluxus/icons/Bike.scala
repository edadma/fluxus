package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/bike", JSImport.Default)
private object BikeIcon extends js.Array[js.Any]

def Bike(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BikeIcon, color, size)
