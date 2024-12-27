package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/folder-up", JSImport.Default)
private object FolderUpIcon extends js.Array[js.Any]

def FolderUp(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FolderUpIcon, color, size)
