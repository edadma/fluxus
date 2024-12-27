package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/folder-closed", JSImport.Default)
private object FolderClosedIcon extends js.Array[js.Any]

def FolderClosed(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FolderClosedIcon, color, size)
