package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/clipboard-paste", JSImport.Default)
private object ClipboardPasteIcon extends js.Array[js.Any]

def ClipboardPaste(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ClipboardPasteIcon, color, size)
