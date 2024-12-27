package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/list-filter-plus", JSImport.Default)
private object ListFilterPlusIcon extends js.Array[js.Any]

def ListFilterPlus(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ListFilterPlusIcon, color, size)
