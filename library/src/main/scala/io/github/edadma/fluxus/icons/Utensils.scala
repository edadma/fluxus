package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/utensils", JSImport.Default)
private object UtensilsIcon extends js.Array[js.Any]

def Utensils(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(UtensilsIcon, color, size)
