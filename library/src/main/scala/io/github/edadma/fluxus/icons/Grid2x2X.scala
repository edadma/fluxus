package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/grid-2x2-x", JSImport.Default)
private object Grid2x2XIcon extends js.Array[js.Any]

def Grid2x2X(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Grid2x2XIcon, color, size)
