package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/folder-root", JSImport.Default)
private object FolderRootIcon extends js.Array[js.Any]

def FolderRoot(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FolderRootIcon, color, size)
