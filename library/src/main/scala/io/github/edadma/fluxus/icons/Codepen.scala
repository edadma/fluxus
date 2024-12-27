package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/codepen", JSImport.Default)
private object CodepenIcon extends js.Array[js.Any]

def Codepen(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(CodepenIcon, color, size)
