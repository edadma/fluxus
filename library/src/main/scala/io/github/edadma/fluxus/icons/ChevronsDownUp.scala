package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/chevrons-down-up", JSImport.Default)
private object ChevronsDownUpIcon extends js.Array[js.Any]

def ChevronsDownUp(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ChevronsDownUpIcon, color, size)
