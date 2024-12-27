package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/folder-pen", JSImport.Default)
private object FolderPenIcon extends js.Array[js.Any]

def FolderPen(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FolderPenIcon, color, size)
