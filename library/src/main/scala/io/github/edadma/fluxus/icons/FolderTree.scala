package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/folder-tree", JSImport.Default)
private object FolderTreeIcon extends js.Array[js.Any]

def FolderTree(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FolderTreeIcon, color, size)
