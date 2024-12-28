package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/file-pen-line", JSImport.Default)
private object FilePenLineIcon extends js.Array[js.Any]

def FilePenLine(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FilePenLineIcon, color, size)