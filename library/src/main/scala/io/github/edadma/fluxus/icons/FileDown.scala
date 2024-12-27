package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/file-down", JSImport.Default)
private object FileDownIcon extends js.Array[js.Any]

def FileDown(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FileDownIcon, color, size)
