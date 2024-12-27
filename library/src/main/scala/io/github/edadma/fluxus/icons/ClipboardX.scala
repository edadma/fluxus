package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/clipboard-x", JSImport.Default)
private object ClipboardXIcon extends js.Array[js.Any]

def ClipboardX(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ClipboardXIcon, color, size)
