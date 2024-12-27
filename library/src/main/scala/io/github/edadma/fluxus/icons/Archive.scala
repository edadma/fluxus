package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/archive", JSImport.Default)
private object ArchiveIcon extends js.Array[js.Any]

def Archive(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ArchiveIcon, color, size)
