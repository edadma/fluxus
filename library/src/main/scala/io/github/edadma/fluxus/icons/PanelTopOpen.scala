package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/panel-top-open", JSImport.Default)
private object PanelTopOpenIcon extends js.Array[js.Any]

def PanelTopOpen(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PanelTopOpenIcon, color, size)
