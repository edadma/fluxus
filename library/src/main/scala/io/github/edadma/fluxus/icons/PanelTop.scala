package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/panel-top", JSImport.Default)
private object PanelTopIcon extends js.Array[js.Any]

def PanelTop(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PanelTopIcon, color, size)
