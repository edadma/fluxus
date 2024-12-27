package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/panel-bottom", JSImport.Default)
private object PanelBottomIcon extends js.Array[js.Any]

def PanelBottom(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PanelBottomIcon, color, size)
