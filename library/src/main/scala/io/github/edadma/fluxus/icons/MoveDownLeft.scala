package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/move-down-left", JSImport.Default)
private object MoveDownLeftIcon extends js.Array[js.Any]

def MoveDownLeft(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MoveDownLeftIcon, color, size)
