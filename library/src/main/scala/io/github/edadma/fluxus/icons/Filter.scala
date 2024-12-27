package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/filter", JSImport.Default)
private object FilterIcon extends js.Array[js.Any]

def Filter(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FilterIcon, color, size)
