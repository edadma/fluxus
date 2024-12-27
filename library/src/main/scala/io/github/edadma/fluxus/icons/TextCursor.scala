package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/text-cursor", JSImport.Default)
private object TextCursorIcon extends js.Array[js.Any]

def TextCursor(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TextCursorIcon, color, size)
