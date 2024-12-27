package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/chevrons-down", JSImport.Default)
private object ChevronsDownIcon extends js.Array[js.Any]

def ChevronsDown(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ChevronsDownIcon, color, size)
