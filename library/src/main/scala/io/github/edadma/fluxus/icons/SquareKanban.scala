package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/square-kanban", JSImport.Default)
private object SquareKanbanIcon extends js.Array[js.Any]

def SquareKanban(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SquareKanbanIcon, color, size)
