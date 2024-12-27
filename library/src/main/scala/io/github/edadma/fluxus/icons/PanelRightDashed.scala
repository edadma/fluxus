package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/panel-right-dashed", JSImport.Default)
private object PanelRightDashedIcon extends js.Array[js.Any]

def PanelRightDashed(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PanelRightDashedIcon, color, size)
