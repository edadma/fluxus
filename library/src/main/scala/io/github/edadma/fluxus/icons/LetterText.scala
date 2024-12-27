package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/letter-text", JSImport.Default)
private object LetterTextIcon extends js.Array[js.Any]

def LetterText(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(LetterTextIcon, color, size)
