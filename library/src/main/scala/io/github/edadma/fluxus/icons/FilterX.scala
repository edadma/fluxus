package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/filter-x", JSImport.Default)
private object FilterXIcon extends js.Array[js.Any]

def FilterX(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(FilterXIcon, color, size)
