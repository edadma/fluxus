package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/grid-2x2-plus", JSImport.Default)
private object Grid2x2PlusIcon extends js.Array[js.Any]

def Grid2x2Plus(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Grid2x2PlusIcon, color, size)
