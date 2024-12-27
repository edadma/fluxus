package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/inspection-panel", JSImport.Default)
private object InspectionPanelIcon extends js.Array[js.Any]

def InspectionPanel(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(InspectionPanelIcon, color, size)
