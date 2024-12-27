package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/move-up-right", JSImport.Default)
private object MoveUpRightIcon extends js.Array[js.Any]

def MoveUpRight(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MoveUpRightIcon, color, size)
