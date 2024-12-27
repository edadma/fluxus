package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/move-down", JSImport.Default)
private object MoveDownIcon extends js.Array[js.Any]

def MoveDown(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MoveDownIcon, color, size)
