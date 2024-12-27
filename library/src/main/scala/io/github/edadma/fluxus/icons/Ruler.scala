package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/ruler", JSImport.Default)
private object RulerIcon extends js.Array[js.Any]

def Ruler(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(RulerIcon, color, size)
