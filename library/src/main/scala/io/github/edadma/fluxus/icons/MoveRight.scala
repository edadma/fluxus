package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/move-right", JSImport.Default)
private object MoveRightIcon extends js.Array[js.Any]

def MoveRight(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MoveRightIcon, color, size)
