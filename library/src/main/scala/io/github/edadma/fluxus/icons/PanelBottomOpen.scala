package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/panel-bottom-open", JSImport.Default)
private object PanelBottomOpenIcon extends js.Array[js.Any]

def PanelBottomOpen(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PanelBottomOpenIcon, color, size)
