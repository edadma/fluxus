package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/panel-top-close", JSImport.Default)
private object PanelTopCloseIcon extends js.Array[js.Any]

def PanelTopClose(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PanelTopCloseIcon, color, size)
