package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/strikethrough", JSImport.Default)
private object StrikethroughIcon extends js.Array[js.Any]

def Strikethrough(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(StrikethroughIcon, color, size)
