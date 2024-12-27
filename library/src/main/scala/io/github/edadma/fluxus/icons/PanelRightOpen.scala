package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/panel-right-open", JSImport.Default)
private object PanelRightOpenIcon extends js.Array[js.Any]

def PanelRightOpen(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PanelRightOpenIcon, color, size)
