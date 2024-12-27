package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/file-key", JSImport.Default)
private object FileKeyIcon extends js.Array[js.Any]

def FileKey(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FileKeyIcon, color, size)
