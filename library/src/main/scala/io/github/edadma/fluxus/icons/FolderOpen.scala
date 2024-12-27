package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/folder-open", JSImport.Default)
private object FolderOpenIcon extends js.Array[js.Any]

def FolderOpen(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FolderOpenIcon, color, size)
