package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/list-filter", JSImport.Default)
private object ListFilterIcon extends js.Array[js.Any]

def ListFilter(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ListFilterIcon, color, size)
