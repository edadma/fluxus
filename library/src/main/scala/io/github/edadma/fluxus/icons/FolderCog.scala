package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/folder-cog", JSImport.Default)
private object FolderCogIcon extends js.Array[js.Any]

def FolderCog(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FolderCogIcon, color, size)
