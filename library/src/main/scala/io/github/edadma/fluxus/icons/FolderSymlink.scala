package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/folder-symlink", JSImport.Default)
private object FolderSymlinkIcon extends js.Array[js.Any]

def FolderSymlink(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FolderSymlinkIcon, color, size)
