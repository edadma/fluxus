package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/corner-right-down", JSImport.Default)
private object CornerRightDownIcon extends js.Array[js.Any]

def CornerRightDown(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CornerRightDownIcon, color, size)
