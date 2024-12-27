package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/move-up-left", JSImport.Default)
private object MoveUpLeftIcon extends js.Array[js.Any]

def MoveUpLeft(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MoveUpLeftIcon, color, size)
