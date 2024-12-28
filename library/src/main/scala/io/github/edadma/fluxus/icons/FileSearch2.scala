package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/file-search-2", JSImport.Default)
private object FileSearch2Icon extends js.Array[js.Any]

def FileSearch2(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FileSearch2Icon, color, size)