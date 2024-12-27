package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/panel-left-open", JSImport.Default)
private object PanelLeftOpenIcon extends js.Array[js.Any]

def PanelLeftOpen(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PanelLeftOpenIcon, color, size)
