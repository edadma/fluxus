package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/wrap-text", JSImport.Default)
private object WrapTextIcon extends js.Array[js.Any]

def WrapText(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(WrapTextIcon, color, size)
