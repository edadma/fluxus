package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/carrot", JSImport.Default)
private object CarrotIcon extends js.Array[js.Any]

def Carrot(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CarrotIcon, color, size)
