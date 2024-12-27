package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/panel-left-close", JSImport.Default)
private object PanelLeftCloseIcon extends js.Array[js.Any]

def PanelLeftClose(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PanelLeftCloseIcon, color, size)
