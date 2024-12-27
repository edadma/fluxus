package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/folder-key", JSImport.Default)
private object FolderKeyIcon extends js.Array[js.Any]

def FolderKey(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FolderKeyIcon, color, size)
