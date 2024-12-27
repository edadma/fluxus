package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/folder-plus", JSImport.Default)
private object FolderPlusIcon extends js.Array[js.Any]

def FolderPlus(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FolderPlusIcon, color, size)
