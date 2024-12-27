package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/clipboard-copy", JSImport.Default)
private object ClipboardCopyIcon extends js.Array[js.Any]

def ClipboardCopy(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ClipboardCopyIcon, color, size)
