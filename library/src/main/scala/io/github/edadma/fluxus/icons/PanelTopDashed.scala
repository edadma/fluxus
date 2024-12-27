package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/panel-top-dashed", JSImport.Default)
private object PanelTopDashedIcon extends js.Array[js.Any]

def PanelTopDashed(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PanelTopDashedIcon, color, size)
