package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/folder-archive", JSImport.Default)
private object FolderArchiveIcon extends js.Array[js.Any]

def FolderArchive(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FolderArchiveIcon, color, size)
