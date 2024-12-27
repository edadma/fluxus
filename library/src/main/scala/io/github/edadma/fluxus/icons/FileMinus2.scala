package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/file-minus-2", JSImport.Default)
private object FileMinus2Icon extends js.Array[js.Any]

def FileMinus2(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FileMinus2Icon, color, size)
