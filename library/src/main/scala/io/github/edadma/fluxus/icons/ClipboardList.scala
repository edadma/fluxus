package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/clipboard-list", JSImport.Default)
private object ClipboardListIcon extends js.Array[js.Any]

def ClipboardList(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ClipboardListIcon, color, size)
