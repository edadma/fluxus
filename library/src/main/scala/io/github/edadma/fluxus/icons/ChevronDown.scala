package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/chevron-down", JSImport.Default)
private object ChevronDownIcon extends js.Array[js.Any]

def ChevronDown(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ChevronDownIcon, color, size)
