package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/folder-check", JSImport.Default)
private object FolderCheckIcon extends js.Array[js.Any]

def FolderCheck(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FolderCheckIcon, color, size)
