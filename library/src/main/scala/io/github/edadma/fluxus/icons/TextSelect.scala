package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/text-select", JSImport.Default)
private object TextSelectIcon extends js.Array[js.Any]

def TextSelect(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TextSelectIcon, color, size)
