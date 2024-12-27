package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/file-pen", JSImport.Default)
private object FilePenIcon extends js.Array[js.Any]

def FilePen(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FilePenIcon, color, size)
