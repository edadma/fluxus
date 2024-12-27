package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/panels-right-bottom", JSImport.Default)
private object PanelsRightBottomIcon extends js.Array[js.Any]

def PanelsRightBottom(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PanelsRightBottomIcon, color, size)
