package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/chevrons-right-left", JSImport.Default)
private object ChevronsRightLeftIcon extends js.Array[js.Any]

def ChevronsRightLeft(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ChevronsRightLeftIcon, color, size)
