package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/clipboard", JSImport.Default)
private object ClipboardIcon extends js.Array[js.Any]

def Clipboard(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ClipboardIcon, color, size)
