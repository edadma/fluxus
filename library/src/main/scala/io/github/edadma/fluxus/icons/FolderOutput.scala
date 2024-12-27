package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/folder-output", JSImport.Default)
private object FolderOutputIcon extends js.Array[js.Any]

def FolderOutput(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FolderOutputIcon, color, size)
