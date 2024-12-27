package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/parentheses", JSImport.Default)
private object ParenthesesIcon extends js.Array[js.Any]

def Parentheses(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ParenthesesIcon, color, size)
