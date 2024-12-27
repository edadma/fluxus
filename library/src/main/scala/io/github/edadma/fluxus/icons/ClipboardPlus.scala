package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/clipboard-plus", JSImport.Default)
private object ClipboardPlusIcon extends js.Array[js.Any]

def ClipboardPlus(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ClipboardPlusIcon, color, size)
