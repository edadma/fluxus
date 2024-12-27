package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/file-box", JSImport.Default)
private object FileBoxIcon extends js.Array[js.Any]

def FileBox(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FileBoxIcon, color, size)
