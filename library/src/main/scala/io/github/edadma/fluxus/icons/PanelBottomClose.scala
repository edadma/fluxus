package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/panel-bottom-close", JSImport.Default)
private object PanelBottomCloseIcon extends js.Array[js.Any]

def PanelBottomClose(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PanelBottomCloseIcon, color, size)
