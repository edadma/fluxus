package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/list-collapse", JSImport.Default)
private object ListCollapseIcon extends js.Array[js.Any]

def ListCollapse(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(ListCollapseIcon, color, size)
