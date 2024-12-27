package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/text-cursor-input", JSImport.Default)
private object TextCursorInputIcon extends js.Array[js.Any]

def TextCursorInput(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TextCursorInputIcon, color, size)
