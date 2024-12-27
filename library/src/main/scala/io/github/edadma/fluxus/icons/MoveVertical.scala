package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/move-vertical", JSImport.Default)
private object MoveVerticalIcon extends js.Array[js.Any]

def MoveVertical(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MoveVerticalIcon, color, size)
