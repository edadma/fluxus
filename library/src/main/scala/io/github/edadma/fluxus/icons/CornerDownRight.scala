package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/corner-down-right", JSImport.Default)
private object CornerDownRightIcon extends js.Array[js.Any]

def CornerDownRight(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CornerDownRightIcon, color, size)
