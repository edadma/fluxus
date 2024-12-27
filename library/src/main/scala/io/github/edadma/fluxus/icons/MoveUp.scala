package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/move-up", JSImport.Default)
private object MoveUpIcon extends js.Array[js.Any]

def MoveUp(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MoveUpIcon, color, size)
