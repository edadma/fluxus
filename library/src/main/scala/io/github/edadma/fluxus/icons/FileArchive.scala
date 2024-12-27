package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/file-archive", JSImport.Default)
private object FileArchiveIcon extends js.Array[js.Any]

def FileArchive(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FileArchiveIcon, color, size)
