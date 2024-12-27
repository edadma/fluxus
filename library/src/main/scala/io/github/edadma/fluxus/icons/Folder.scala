package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/folder", JSImport.Default)
private object FolderIcon extends js.Array[js.Any]

def Folder(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FolderIcon, color, size)
