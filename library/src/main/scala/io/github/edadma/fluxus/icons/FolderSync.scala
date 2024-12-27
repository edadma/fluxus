package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/folder-sync", JSImport.Default)
private object FolderSyncIcon extends js.Array[js.Any]

def FolderSync(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FolderSyncIcon, color, size)
