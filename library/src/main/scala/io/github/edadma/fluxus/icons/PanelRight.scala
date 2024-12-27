package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/panel-right", JSImport.Default)
private object PanelRightIcon extends js.Array[js.Any]

def PanelRight(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PanelRightIcon, color, size)
