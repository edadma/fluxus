package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/folder-code", JSImport.Default)
private object FolderCodeIcon extends js.Array[js.Any]

def FolderCode(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FolderCodeIcon, color, size)
