package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/table-cells-merge", JSImport.Default)
private object TableCellsMergeIcon extends js.Array[js.Any]

def TableCellsMerge(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TableCellsMergeIcon, color, size)
