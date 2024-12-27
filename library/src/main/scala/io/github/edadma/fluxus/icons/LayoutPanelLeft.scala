package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/layout-panel-left", JSImport.Default)
private object LayoutPanelLeftIcon extends js.Array[js.Any]

def LayoutPanelLeft(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(LayoutPanelLeftIcon, color, size)
