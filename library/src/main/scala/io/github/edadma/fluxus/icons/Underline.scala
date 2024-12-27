package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/underline", JSImport.Default)
private object UnderlineIcon extends js.Array[js.Any]

def Underline(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(UnderlineIcon, color, size)
