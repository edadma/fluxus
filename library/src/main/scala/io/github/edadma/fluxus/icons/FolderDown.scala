package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/folder-down", JSImport.Default)
private object FolderDownIcon extends js.Array[js.Any]

def FolderDown(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FolderDownIcon, color, size)
