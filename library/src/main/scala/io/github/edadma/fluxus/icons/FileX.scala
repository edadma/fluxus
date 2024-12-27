package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/file-x", JSImport.Default)
private object FileXIcon extends js.Array[js.Any]

def FileX(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FileXIcon, color, size)
