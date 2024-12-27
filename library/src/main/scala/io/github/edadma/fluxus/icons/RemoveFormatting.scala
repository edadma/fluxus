package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/remove-formatting", JSImport.Default)
private object RemoveFormattingIcon extends js.Array[js.Any]

def RemoveFormatting(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(RemoveFormattingIcon, color, size)
