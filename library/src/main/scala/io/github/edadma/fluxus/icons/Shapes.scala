package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/shapes", JSImport.Default)
private object ShapesIcon extends js.Array[js.Any]

def Shapes(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ShapesIcon, color, size)
