package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/mouse-pointer-ban", JSImport.Default)
private object MousePointerBanIcon extends js.Array[js.Any]

def MousePointerBan(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(MousePointerBanIcon, color, size)
