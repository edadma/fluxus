package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/table-rows-split", JSImport.Default)
private object TableRowsSplitIcon extends js.Array[js.Any]

def TableRowsSplit(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TableRowsSplitIcon, color, size)
