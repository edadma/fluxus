package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/move-horizontal", JSImport.Default)
private object MoveHorizontalIcon extends js.Array[js.Any]

def MoveHorizontal(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MoveHorizontalIcon, color, size)
