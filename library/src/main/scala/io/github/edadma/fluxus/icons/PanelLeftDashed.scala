package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/panel-left-dashed", JSImport.Default)
private object PanelLeftDashedIcon extends js.Array[js.Any]

def PanelLeftDashed(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PanelLeftDashedIcon, color, size)
