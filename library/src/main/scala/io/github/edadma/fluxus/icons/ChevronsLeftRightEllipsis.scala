package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/chevrons-left-right-ellipsis", JSImport.Default)
private object ChevronsLeftRightEllipsisIcon extends js.Array[js.Any]

def ChevronsLeftRightEllipsis(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ChevronsLeftRightEllipsisIcon, color, size)
