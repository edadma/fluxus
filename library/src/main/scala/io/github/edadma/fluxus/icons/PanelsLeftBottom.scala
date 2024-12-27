package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/panels-left-bottom", JSImport.Default)
private object PanelsLeftBottomIcon extends js.Array[js.Any]

def PanelsLeftBottom(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(PanelsLeftBottomIcon, color, size)
