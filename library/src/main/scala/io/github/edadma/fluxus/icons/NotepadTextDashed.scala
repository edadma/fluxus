package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/notepad-text-dashed", JSImport.Default)
private object NotepadTextDashedIcon extends js.Array[js.Any]

def NotepadTextDashed(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(NotepadTextDashedIcon, color, size)
