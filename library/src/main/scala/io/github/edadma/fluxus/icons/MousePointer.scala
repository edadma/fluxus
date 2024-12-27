package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/mouse-pointer", JSImport.Default)
private object MousePointerIcon extends js.Array[js.Any]

def MousePointer(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MousePointerIcon, color, size)
