package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/archive-restore", JSImport.Default)
private object ArchiveRestoreIcon extends js.Array[js.Any]

def ArchiveRestore(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ArchiveRestoreIcon, color, size)
