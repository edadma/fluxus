package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/clipboard-check", JSImport.Default)
private object ClipboardCheckIcon extends js.Array[js.Any]

def ClipboardCheck(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ClipboardCheckIcon, color, size)
