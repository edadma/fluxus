package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/folder-kanban", JSImport.Default)
private object FolderKanbanIcon extends js.Array[js.Any]

def FolderKanban(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FolderKanbanIcon, color, size)
