package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/folder-open-dot", JSImport.Default)
private object FolderOpenDotIcon extends js.Array[js.Any]

def FolderOpenDot(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FolderOpenDotIcon, color, size)
