package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/clipboard-pen-line", JSImport.Default)
private object ClipboardPenLineIcon extends js.Array[js.Any]

def ClipboardPenLine(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ClipboardPenLineIcon, color, size)
