package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/file", JSImport.Default)
private object FileIcon extends js.Array[js.Any]

def File(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FileIcon, color, size)
