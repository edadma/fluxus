package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/undo", JSImport.Default)
private object UndoIcon extends js.Array[js.Any]

def Undo(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(UndoIcon, color, size)
