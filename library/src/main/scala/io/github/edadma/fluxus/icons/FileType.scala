package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/file-type", JSImport.Default)
private object FileTypeIcon extends js.Array[js.Any]

def FileType(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FileTypeIcon, color, size)
