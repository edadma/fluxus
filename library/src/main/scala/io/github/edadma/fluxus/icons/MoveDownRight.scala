package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/move-down-right", JSImport.Default)
private object MoveDownRightIcon extends js.Array[js.Any]

def MoveDownRight(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MoveDownRightIcon, color, size)
