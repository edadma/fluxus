package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/folder-x", JSImport.Default)
private object FolderXIcon extends js.Array[js.Any]

def FolderX(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FolderXIcon, color, size)
