package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/move-left", JSImport.Default)
private object MoveLeftIcon extends js.Array[js.Any]

def MoveLeft(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MoveLeftIcon, color, size)
