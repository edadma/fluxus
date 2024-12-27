package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/archive-x", JSImport.Default)
private object ArchiveXIcon extends js.Array[js.Any]

def ArchiveX(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ArchiveXIcon, color, size)
