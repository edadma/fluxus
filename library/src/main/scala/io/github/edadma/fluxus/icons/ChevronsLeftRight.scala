package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/chevrons-left-right", JSImport.Default)
private object ChevronsLeftRightIcon extends js.Array[js.Any]

def ChevronsLeftRight(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ChevronsLeftRightIcon, color, size)
