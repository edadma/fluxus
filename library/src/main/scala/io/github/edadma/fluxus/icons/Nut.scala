package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/nut", JSImport.Default)
private object NutIcon extends js.Array[js.Any]

def Nut(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(NutIcon, color, size)
