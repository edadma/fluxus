package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/file-search", JSImport.Default)
private object FileSearchIcon extends js.Array[js.Any]

def FileSearch(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FileSearchIcon, color, size)
