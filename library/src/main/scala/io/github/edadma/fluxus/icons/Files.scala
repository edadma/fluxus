package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/files", JSImport.Default)
private object FilesIcon extends js.Array[js.Any]

def Files(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FilesIcon, color, size)
