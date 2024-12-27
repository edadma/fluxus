package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/brackets", JSImport.Default)
private object BracketsIcon extends js.Array[js.Any]

def Brackets(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(BracketsIcon, color, size)
