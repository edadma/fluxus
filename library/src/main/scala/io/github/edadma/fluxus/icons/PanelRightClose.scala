package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/panel-right-close", JSImport.Default)
private object PanelRightCloseIcon extends js.Array[js.Any]

def PanelRightClose(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PanelRightCloseIcon, color, size)
