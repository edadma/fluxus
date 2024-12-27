package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/folder-dot", JSImport.Default)
private object FolderDotIcon extends js.Array[js.Any]

def FolderDot(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FolderDotIcon, color, size)
