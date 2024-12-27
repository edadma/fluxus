package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/dessert", JSImport.Default)
private object DessertIcon extends js.Array[js.Any]

def Dessert(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(DessertIcon, color, size)
