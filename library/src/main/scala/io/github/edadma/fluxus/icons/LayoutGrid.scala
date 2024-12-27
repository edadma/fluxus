package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/layout-grid", JSImport.Default)
private object LayoutGridIcon extends js.Array[js.Any]

def LayoutGrid(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(LayoutGridIcon, color, size)
