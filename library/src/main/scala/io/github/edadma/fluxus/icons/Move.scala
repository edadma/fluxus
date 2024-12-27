package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/move", JSImport.Default)
private object MoveIcon extends js.Array[js.Any]

def Move(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MoveIcon, color, size)
