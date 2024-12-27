package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/folder-search", JSImport.Default)
private object FolderSearchIcon extends js.Array[js.Any]

def FolderSearch(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FolderSearchIcon, color, size)
