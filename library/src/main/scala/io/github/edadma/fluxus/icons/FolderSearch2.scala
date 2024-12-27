package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/folder-search-2", JSImport.Default)
private object FolderSearch2Icon extends js.Array[js.Any]

def FolderSearch2(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FolderSearch2Icon, color, size)
