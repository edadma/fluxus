package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/chevrons-right", JSImport.Default)
private object ChevronsRightIcon extends js.Array[js.Any]

def ChevronsRight(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ChevronsRightIcon, color, size)
