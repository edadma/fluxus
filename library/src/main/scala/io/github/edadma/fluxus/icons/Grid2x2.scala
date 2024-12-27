package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/grid-2x2", JSImport.Default)
private object Grid2x2Icon extends js.Array[js.Any]

def Grid2x2(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(Grid2x2Icon, color, size)
