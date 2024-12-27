package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/folder-clock", JSImport.Default)
private object FolderClockIcon extends js.Array[js.Any]

def FolderClock(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FolderClockIcon, color, size)
