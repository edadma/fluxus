package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/table-columns-split", JSImport.Default)
private object TableColumnsSplitIcon extends js.Array[js.Any]

def TableColumnsSplit(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(TableColumnsSplitIcon, color, size)
