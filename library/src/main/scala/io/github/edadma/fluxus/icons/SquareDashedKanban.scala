package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/square-dashed-kanban", JSImport.Default)
private object SquareDashedKanbanIcon extends js.Array[js.Any]

def SquareDashedKanban(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(SquareDashedKanbanIcon, color, size)
