package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/layout-panel-top", JSImport.Default)
private object LayoutPanelTopIcon extends js.Array[js.Any]

def LayoutPanelTop(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(LayoutPanelTopIcon, color, size)
