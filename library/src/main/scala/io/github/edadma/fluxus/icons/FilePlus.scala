package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/file-plus", JSImport.Default)
private object FilePlusIcon extends js.Array[js.Any]

def FilePlus(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FilePlusIcon, color, size)
