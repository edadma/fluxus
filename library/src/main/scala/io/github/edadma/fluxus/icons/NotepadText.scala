package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/notepad-text", JSImport.Default)
private object NotepadTextIcon extends js.Array[js.Any]

def NotepadText(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(NotepadTextIcon, color, size)
