package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/sticky-note", JSImport.Default)
private object StickyNoteIcon extends js.Array[js.Any]

def StickyNote(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(StickyNoteIcon, color, size)
