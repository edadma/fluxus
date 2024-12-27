package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/mouse-pointer-click", JSImport.Default)
private object MousePointerClickIcon extends js.Array[js.Any]

def MousePointerClick(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MousePointerClickIcon, color, size)
