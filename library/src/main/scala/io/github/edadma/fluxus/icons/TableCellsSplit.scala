package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/table-cells-split", JSImport.Default)
private object TableCellsSplitIcon extends js.Array[js.Any]

def TableCellsSplit(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TableCellsSplitIcon, color, size)
