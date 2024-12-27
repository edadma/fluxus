package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/donut", JSImport.Default)
private object DonutIcon extends js.Array[js.Any]

def Donut(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(DonutIcon, color, size)
