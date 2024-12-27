package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/chevron-last", JSImport.Default)
private object ChevronLastIcon extends js.Array[js.Any]

def ChevronLast(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ChevronLastIcon, color, size)
