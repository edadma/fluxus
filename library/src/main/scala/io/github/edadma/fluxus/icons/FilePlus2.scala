package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/file-plus-2", JSImport.Default)
private object FilePlus2Icon extends js.Array[js.Any]

def FilePlus2(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FilePlus2Icon, color, size)
