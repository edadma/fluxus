package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/panel-left", JSImport.Default)
private object PanelLeftIcon extends js.Array[js.Any]

def PanelLeft(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PanelLeftIcon, color, size)
