package io.github.edadma.fluxus.icons

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import io.github.edadma.fluxus.RawNode

@js.native
@JSImport("lucide/dist/esm/icons/grip-horizontal", JSImport.Default)
private object GripHorizontalIcon extends js.Array[js.Any]

def GripHorizontal(size: Int = 24, color: String = "currentColor"): RawNode =
  createIcon(GripHorizontalIcon, color, size)
