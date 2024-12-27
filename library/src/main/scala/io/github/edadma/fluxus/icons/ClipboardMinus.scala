package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/clipboard-minus", JSImport.Default)
private object ClipboardMinusIcon extends js.Array[js.Any]

def ClipboardMinus(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ClipboardMinusIcon, color, size)
