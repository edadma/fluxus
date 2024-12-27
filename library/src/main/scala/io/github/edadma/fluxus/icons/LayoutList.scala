package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/layout-list", JSImport.Default)
private object LayoutListIcon extends js.Array[js.Any]

def LayoutList(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(LayoutListIcon, color, size)
